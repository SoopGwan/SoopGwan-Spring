package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetArchiveWeekHabitResponse {

    private final List<ArchiveWeekHabitElement> habits;
    private final Integer status;
    private final Long id;
}