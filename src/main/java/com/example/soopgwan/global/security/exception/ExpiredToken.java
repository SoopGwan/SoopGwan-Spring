package com.example.soopgwan.global.security.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class ExpiredToken extends CustomException {

    public static final CustomException EXCEPTION = new ExpiredToken();

    private ExpiredToken() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
