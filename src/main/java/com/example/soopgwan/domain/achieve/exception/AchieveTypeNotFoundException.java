package com.example.soopgwan.domain.achieve.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class AchieveTypeNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new AchieveTypeNotFoundException();

    private AchieveTypeNotFoundException() {
        super(ErrorCode.ACHIEVE_TYPE_NOT_FOUND);
    }
}
