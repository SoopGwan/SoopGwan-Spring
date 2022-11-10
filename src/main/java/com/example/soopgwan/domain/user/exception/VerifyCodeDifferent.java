package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class VerifyCodeDifferent extends CustomException {

    public static final CustomException EXCEPTION =
            new VerifyCodeDifferent();

    private VerifyCodeDifferent() {
        super(ErrorCode.VERIFY_CODE_DIFFERENT);
    }
}
