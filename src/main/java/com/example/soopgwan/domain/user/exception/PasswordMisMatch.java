package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class PasswordMisMatch extends CustomException {

    public static final PasswordMisMatch EXCEPTION =
            new PasswordMisMatch();

    private PasswordMisMatch() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
