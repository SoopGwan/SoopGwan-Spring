package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeekHabitElement {

    private final Long id;
    private final String content;
    private final Integer successCount;
    private final Boolean successStatus;
}
