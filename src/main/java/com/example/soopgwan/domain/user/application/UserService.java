package com.example.soopgwan.domain.user.application;

import com.example.soopgwan.domain.habit.application.enums.Date;
import com.example.soopgwan.domain.habit.persistence.WeekHabitStatus;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitStatusRepository;
import com.example.soopgwan.domain.user.exception.InvalidCodeType;
import com.example.soopgwan.domain.user.exception.PasswordDifferent;
import com.example.soopgwan.domain.user.exception.PasswordMisMatch;
import com.example.soopgwan.domain.user.exception.TooManySendCode;
import com.example.soopgwan.domain.user.exception.UserExists;
import com.example.soopgwan.domain.user.exception.UserNotFound;
import com.example.soopgwan.domain.user.exception.VerifyCodeDifferent;
import com.example.soopgwan.domain.user.exception.VerifyCodeExpired;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.domain.user.persistence.VerifyCode;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.domain.user.persistence.repository.VerifyCodeRepository;
import com.example.soopgwan.domain.user.presentation.dto.request.ChangePasswordRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.LoginRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.ResetPasswordRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.SendCodeRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.SignUpRequset;
import com.example.soopgwan.domain.user.presentation.dto.request.VerifyCodeRequest;
import com.example.soopgwan.domain.user.presentation.dto.response.ResetPasswordResponse;
import com.example.soopgwan.domain.user.presentation.dto.response.TokenResponse;
import com.example.soopgwan.global.security.jwt.JwtTokenProvider;
import com.example.soopgwan.global.util.CalenderUtil;
import com.example.soopgwan.global.util.RandomPasswordUtil;
import com.example.soopgwan.global.util.UserUtil;
import com.example.soopgwan.infrastructure.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtil userUtil;
    private final VerifyCodeRepository verifyCodeRepository;
    private final MessageUtil messageUtil;
    private final RandomPasswordUtil randomPasswordUtil;
    private final CalenderUtil calenderUtil;
    private final WeekHabitStatusRepository weekHabitStatusRepository;

    @Transactional
    public TokenResponse signUp(SignUpRequset request) {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw UserExists.EXCEPTION;
        }

        User user = User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(user);

        WeekHabitStatus weekHabitStatus = WeekHabitStatus.builder()
                .startAt(calenderUtil.getStartAtAndEndAt(Date.START_AT))
                .endAt(calenderUtil.getStartAtAndEndAt(Date.END_AT))
                .status(0)
                .user(user)
                .build();
        weekHabitStatusRepository.save(weekHabitStatus);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());
        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFound.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatch.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());
        return new TokenResponse(accessToken, refreshToken);
    }

    public void overLapCheck(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw UserExists.EXCEPTION;
        }
    }

    @Transactional()
    public void changePassword(ChangePasswordRequest request) {

        User user = userUtil.getCurrentUser();

        if (!request.getPassword().equals(request.getRepeatPassword())) {
            throw PasswordDifferent.EXCEPTION;
        }

        user.changePassword(passwordEncoder.encode(request.getPassword()));
    }

    public void sendCode(SendCodeRequest request) {
        int count = verifyCodeRepository.findById(request.getPhoneNumber()).isEmpty() ?
                0 : verifyCodeRepository.findById(request.getPhoneNumber()).get().getCount();

        if (count >= 5) {
            throw TooManySendCode.EXCEPTION;
        }

        messageUtil.send(request.getPhoneNumber(), count, request.getType());
    }

    public void verifyCode(VerifyCodeRequest request) {
        VerifyCode verifyCode = verifyCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> VerifyCodeExpired.EXCEPTION);

        if (!verifyCode.getCode().equals(request.getCode())) {
            throw VerifyCodeDifferent.EXCEPTION;
        }

        if (!verifyCode.getType().equals(request.getType())) {
            throw InvalidCodeType.EXCEPTION;
        }
    }

    @Transactional()
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> UserNotFound.EXCEPTION);

        StringBuilder password = randomPasswordUtil.getRandomPassword();
        user.changePassword(passwordEncoder.encode(password.toString()));

        return new ResetPasswordResponse(password.toString());
    }
}
