package com.example.soopgwan.global.security.jwt;

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

    public String generateToken(String id) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(id)
                .claim("type", ACCESS_TYPE)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000))
                .compact();
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
