package com.example.soopgwan.domain.user.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class UserExists extends CustomException {

    public static final CustomException EXCEPTION =
            new UserExists();

    private UserExists() {
        super(ErrorCode.USER_EXISTS);
    }
}