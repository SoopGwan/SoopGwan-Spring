package com.example.soopgwan.domain.user.application;

import com.example.soopgwan.domain.user.exception.*;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.domain.user.persistence.VerifyCode;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.domain.user.persistence.repository.VerifyCodeRepository;
import com.example.soopgwan.domain.user.presentation.dto.request.*;
import com.example.soopgwan.domain.user.presentation.dto.response.TokenResponse;
import com.example.soopgwan.global.security.jwt.JwtTokenProvider;
import com.example.soopgwan.global.util.UserUtil;
import com.example.soopgwan.infrastructure.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtil userUtil;
    private final VerifyCodeRepository verifyCodeRepository;
    private final MessageUtil messageUtil;

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

        messageUtil.send(request.getPhoneNumber(), count);
    }

    public void verifyCode(VerifyCodeRequest request) {
        VerifyCode verifyCode = verifyCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> VerifyCodeExpired.EXCEPTION);

        if (!verifyCode.getCode().equals(request.getCode())) {
            throw VerifyCodeDifferent.EXCEPTION;
        }
    }
}