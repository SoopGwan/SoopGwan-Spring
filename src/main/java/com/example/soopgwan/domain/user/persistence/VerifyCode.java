package com.example.soopgwan.domain.user.persistence;

import com.example.soopgwan.global.enums.CodeType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("VerifyCode")
@Builder
public class VerifyCode {

    @Id
    private String phoneNumber;

    private String code;

    private Integer count;

    private CodeType type;

    @TimeToLive
    private long ttl;
}