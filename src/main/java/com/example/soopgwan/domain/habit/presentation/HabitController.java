package com.example.soopgwan.domain.habit.presentation;

import com.example.soopgwan.domain.habit.application.HabitService;
import com.example.soopgwan.domain.habit.presentation.dto.request.CheckWeekHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.response.ReferWeekHabitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    @DeleteMapping("/{habit-id}")
    public void deleteHabit(@PathVariable("habit-id") Long habitId) {
        habitService.deleteHabit(habitId);
    }

    @PatchMapping("/day/check/{habit-id}")
    public void checkHabitSuccess(@PathVariable("habit-id") Long habitId) {
        habitService.checkHabitSuccess(habitId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/week/check")
    public void checkWeekHabit(@RequestBody @Valid CheckWeekHabitRequest request) {
        habitService.checkWeekHabit(request);
    }

    @GetMapping
    public ReferWeekHabitResponse referWeekHabit() {
        return habitService.referWeekHabit();
    }
}
