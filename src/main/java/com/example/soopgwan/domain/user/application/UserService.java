package com.example.soopgwan.domain.user.application;

import com.example.soopgwan.domain.user.exception.PasswordMisMatch;
import com.example.soopgwan.domain.user.exception.UserExists;
import com.example.soopgwan.domain.user.exception.UserNotFound;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.domain.user.presentation.dto.request.LoginRequest;
import com.example.soopgwan.domain.user.presentation.dto.request.SignUpRequset;
import com.example.soopgwan.domain.user.presentation.dto.response.TokenResponse;
import com.example.soopgwan.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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

        String accessToken = jwtTokenProvider.generateToken(user.getAccountId(), "access");
        String refreshToken = jwtTokenProvider.generateToken(user.getAccountId(), "refresh");
        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFound.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatch.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateToken(user.getAccountId(), "access");
        String refreshToken = jwtTokenProvider.generateToken(user.getAccountId(), "refresh");
        return new TokenResponse(accessToken, refreshToken);
    }
}
