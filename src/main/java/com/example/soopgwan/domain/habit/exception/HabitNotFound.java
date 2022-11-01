package com.example.soopgwan.domain.habit.exception;

import com.example.soopgwan.global.error.CustomException;
import com.example.soopgwan.global.error.ErrorCode;

public class HabitNotFound extends CustomException {

    public static final CustomException EXCEPTION = new HabitNotFound();

    private HabitNotFound() {
        super(ErrorCode.HABIT_NOT_FOUND);
    }
}
