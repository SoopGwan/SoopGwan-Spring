package com.example.soopgwan.domain.user.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("RefreshToken")
@Builder
public class RefreshToken {

    @Id
    private String accountId;

    @Indexed
    private String token;

    @TimeToLive
    private Long ttl;

    public void updateToken(String refreshToken) {
        this.token = refreshToken;
    }
}