package com.example.soopgwan.domain.habit.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class ExistsHabitSuccess extends CustomException {

    public static final CustomException EXCEPTION = new ExistsHabitSuccess();

    private ExistsHabitSuccess() {
        super(ErrorCode.HABIT_ALREADY_EXISTS);
    }
}
