package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class SendCodeError extends CustomException {

    public static final CustomException EXCEPTION =
            new SendCodeError();

    private SendCodeError() {
        super(ErrorCode.SEND_CODE_ERROR);
    }
}
