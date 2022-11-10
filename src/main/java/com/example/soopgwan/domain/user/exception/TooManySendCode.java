package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class TooManySendCode extends CustomException {

    public static final CustomException EXCEPTION =
            new TooManySendCode();

    private TooManySendCode() {
        super(ErrorCode.TOO_MANY_SEND_CODE);
    }
}
