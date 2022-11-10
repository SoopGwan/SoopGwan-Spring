package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class VerifyCodeExpired extends CustomException {

    public static final CustomException EXCEPTION =
            new VerifyCodeExpired();

    private VerifyCodeExpired(){
        super(ErrorCode.VERIFY_CODE_EXPIRED);
    }
}
