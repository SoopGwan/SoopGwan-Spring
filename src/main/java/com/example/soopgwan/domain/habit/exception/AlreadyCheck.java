package com.example.soopgwan.domain.habit.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class AlreadyCheck extends CustomException {

    public static final CustomException EXCEPTION =
            new AlreadyCheck();

    private AlreadyCheck() {
        super(ErrorCode.ALREADY_CHECK);
    }
}
