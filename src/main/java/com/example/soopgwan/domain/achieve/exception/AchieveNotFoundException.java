package com.example.soopgwan.domain.achieve.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class AchieveNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new AchieveNotFoundException();

    private AchieveNotFoundException() {
        super(ErrorCode.ACHIEVE_NOT_FOUND);
    }
}