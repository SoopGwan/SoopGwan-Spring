package com.example.soopgwan.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    EXPIRED_TOKEN(401, "토큰 만료"),
    INVALID_TOKEN(401, "토큰 무효"),

    USER_NOT_FOUND(404, "유저를 찾을 수 없음"),
    INTERNAL_SERVER_ERROR(500, "서버 에러");

    private final int statusCode;
    private final String message;
}
