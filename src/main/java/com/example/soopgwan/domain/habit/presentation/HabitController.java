package com.example.soopgwan.domain.habit.presentation;

import com.example.soopgwan.domain.habit.application.HabitService;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/habit")
@RestController
public class HabitController {
    private final HabitService habitService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createHabit(CreateHabitRequest request) {
        habitService.createHabit(request);
    }
}
