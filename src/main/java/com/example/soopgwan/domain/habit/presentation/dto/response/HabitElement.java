package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class HabitElement {

    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer level;
}
