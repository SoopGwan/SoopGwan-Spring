package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class CodeTypeDifferent extends CustomException {

    public static final CustomException EXCEPTION =
            new CodeTypeDifferent();

    private CodeTypeDifferent() {
        super(ErrorCode.CODE_TYPE_DIFFERENT);
    }
}
