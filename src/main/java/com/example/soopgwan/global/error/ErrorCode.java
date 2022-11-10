package com.example.soopgwan.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    EXPIRED_TOKEN(401, "토큰 만료"),
    INVALID_TOKEN(401, "잘못된 토큰"),
    PASSWORD_MISMATCH(401, "비밀번호가 잘못됨"),
    PASSWORD_DIFFERENT(401, "비밀번호가 일치하지 않음"),
    VERIFY_CODE_DIFFERENT(401, "인증코드가 일치하지 않음"),

    VERIFY_CODE_EXPIRED(403, "인증 코드가 만료됨"),

    USER_NOT_FOUND(404, "유저를 찾을 수 없음"),
    HABIT_NOT_FOUND(404, "습관을 찾을 수 없음"),

    HABIT_ALREADY_EXISTS(409, "습관이 이미 존재함"),

    USER_EXISTS(409, "유저 아이디 중복"),

    TOO_MANY_SEND_CODE(429, "너무 많은 인증코드 요청"),

    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    SEND_CODE_ERROR(500, "Coolsms 에러");

    private final int statusCode;
    private final String message;
}
