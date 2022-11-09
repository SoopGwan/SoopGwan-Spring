package com.example.soopgwan.domain.user.application;

import com.example.soopgwan.domain.user.exception.*;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.domain.user.persistence.VerifyCode;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.domain.user.persistence.repository.VerifyCodeRepository;
import com.example.soopgwan.domain.user.presentation.dto.request.ChangePasswordRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.LoginRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.SendCodeRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.SignUpRequset;
import com.example.soopgwan.domain.user.presentation.dto.response.TokenResponse;
import com.example.soopgwan.global.security.jwt.JwtProperties;
import com.example.soopgwan.global.security.jwt.JwtTokenProvider;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtil userUtil;
    private final VerifyCodeRepository verifyCodeRepository;
    private final JwtProperties jwtProperties;
    @Value("${coolsms.api-key}")
    private String apiKey;
    @Value("${coolsms.api-secret}")
    private String apiSecret;
    @Value("${coolsms.sender-number}")
    private String senderNumber;

    public TokenResponse signUp(SignUpRequset request) {
        if (userRepository.findByAccountId(request.getAccountId()).isPresent()) {
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
        Integer count = verifyCodeRepository.findById(request.getPhoneNumber()).isEmpty() ?
                0 : verifyCodeRepository.findById(request.getPhoneNumber()).get().getCount();
        if (count >= 5) {
            throw TooManySendCode.EXCEPTION;
        }
        Message coolsms = new Message(apiKey, apiSecret);

        Random rand = new Random();
        String code = RandomStringUtils.randomNumeric(4);
        HashMap<String, String> params = new HashMap<>();
        params.put("to", request.getPhoneNumber());
        params.put("from", senderNumber);
        params.put("type", "SMS");
        params.put("text", "[SOOPGWAN] 숲관 인증번호는 [" + code + "] 입니다.");
        try {
            coolsms.send(params);
        }catch(CoolsmsException e){
            e.printStackTrace();
            System.out.println(e.getCode());
        }
        VerifyCode verifyCode = VerifyCode.builder()
                .phoneNumber(request.getPhoneNumber())
                .code(code)
                .count(count + 1)
                .ttl(300)
                .build();
        verifyCodeRepository.save(verifyCode);

    }
}