package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeekHabitElement {

    Long id;

    String content;

    Boolean successStatus;
}
