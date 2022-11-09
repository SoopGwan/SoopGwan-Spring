package com.example.soopgwan.global.security.jwt;

import com.example.soopgwan.domain.user.persistence.RefreshToken;
import com.example.soopgwan.domain.user.persistence.repository.RefreshTokenRepository;
import com.example.soopgwan.global.security.auth.AuthDetailsService;
import com.example.soopgwan.global.security.exception.ExpiredToken;
import com.example.soopgwan.global.security.exception.InvalidToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    private String generateToken(String accountId, String type, long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(accountId)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refresh = generateToken(accountId, "refresh", jwtProperties.getRefreshExp());
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .refreshToken(refresh)
                        .ttl(jwtProperties.getRefreshExp())
                        .build());
        return refresh;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(parseTokenBody(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String parseTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).
                    parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw ExpiredToken.EXCEPTION;
        } catch (Exception e) {
            throw InvalidToken.EXCEPTION;
        }
    }
}
