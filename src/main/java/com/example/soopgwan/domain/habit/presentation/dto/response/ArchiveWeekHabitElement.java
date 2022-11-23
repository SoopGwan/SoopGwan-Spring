package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArchiveWeekHabitElement {

    private final String title;
    private final Integer count;
}
