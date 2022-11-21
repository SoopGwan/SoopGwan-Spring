package com.example.soopgwan.domain.habit.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class ExistsHabitStatus extends CustomException {

    public static final CustomException EXCEPTION = new ExistsHabitStatus();

    private ExistsHabitStatus() {
        super(ErrorCode.EXISTS_HABIT_STATUS);
    }
}
