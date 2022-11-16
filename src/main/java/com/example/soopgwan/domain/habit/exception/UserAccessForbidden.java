package com.example.soopgwan.domain.habit.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class UserAccessForbidden extends CustomException {

    public static final CustomException EXCEPTION = new UserAccessForbidden();

    private UserAccessForbidden() {
        super(ErrorCode.USER_ACCESS_FORBIDDEN);
    }
}
