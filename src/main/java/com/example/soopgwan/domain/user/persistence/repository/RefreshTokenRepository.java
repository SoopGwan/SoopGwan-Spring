package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findRefreshToken(String refreshToken);
}