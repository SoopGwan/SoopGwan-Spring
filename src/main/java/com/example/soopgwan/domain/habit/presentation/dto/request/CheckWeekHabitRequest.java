package com.example.soopgwan.domain.habit.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CheckWeekHabitRequest {

    @NotNull
    private Integer id;

    @NotNull
    private Integer status;
}
