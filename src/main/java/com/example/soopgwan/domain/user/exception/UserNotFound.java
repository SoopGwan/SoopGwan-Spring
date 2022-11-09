package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class UserNotFound extends CustomException {

    public static final CustomException EXCEPTION =
            new UserNotFound();

    private UserNotFound() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
