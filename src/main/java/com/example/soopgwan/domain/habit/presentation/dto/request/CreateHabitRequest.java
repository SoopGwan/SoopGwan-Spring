package com.example.soopgwan.domain.habit.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateHabitRequest {

    @NotBlank
    private String content;
}
