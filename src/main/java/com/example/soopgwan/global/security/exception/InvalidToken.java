package com.example.soopgwan.global.security.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class InvalidToken extends CustomException {

    public static final CustomException EXCEPTION = new InvalidToken();

    private InvalidToken() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
