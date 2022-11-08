package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeekHabitElement {

    private Long id;

    private String content;

    private Boolean successStatus;
}
