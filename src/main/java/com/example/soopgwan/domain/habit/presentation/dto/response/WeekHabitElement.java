package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeekHabitElement {

    private final Long id;

    private final String content;

    private final Boolean successStatus;
}
