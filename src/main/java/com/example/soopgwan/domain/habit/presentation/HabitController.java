package com.example.soopgwan.domain.habit.presentation;

import com.example.soopgwan.domain.habit.application.HabitService;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/habit")
@RestController
public class HabitController {

    private final HabitService habitService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createHabit(@RequestBody @Valid CreateHabitRequest request) {
        habitService.createHabit(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{{habit-id}")
    public void deleteHabit(@PathVariable("habit-id") Long habitId) {
        habitService.deleteHabit(habitId);
    }
}
