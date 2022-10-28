package com.example.soopgwan.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    BAD_REQUEST(400, "Request가 잘못됨"),

    NOT_FOUND(404, "해당 id가 존재하지 않음"),
    HABIT_DELETE_NOT_FOUND(404, "해당 id가 존재하지 않음"),
    HABIT_SUCCESS_NOT_FOUND(404, "해당 id 습관이 없음"),
    HABIT_NOT_FOUND(404, "habit id가 없음"),
    INTERNAL_SERVER_ERROR(500, "서버 에러");

    private final int statusCode;
    private final String message;
}
