package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class HabitElement {

    private final Long id;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer level;
}
