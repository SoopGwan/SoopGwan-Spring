package com.example.soopgwan.domain.habit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HabitResponse {

    private final List<HabitElement> habits;
}
