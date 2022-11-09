package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class PasswordDifferent extends CustomException {

    public static final PasswordDifferent EXCEPTION =
            new PasswordDifferent();

    private PasswordDifferent() {
        super(ErrorCode.PASSWORD_DIFFERENT);
    }
}
