package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class InvalidCodeType extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidCodeType();

    private InvalidCodeType() {
        super(ErrorCode.INVALID_CODE_TYPE);
    }
}
