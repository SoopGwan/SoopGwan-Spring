package com.example.soopgwan.domain.habit.application;

import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import com.example.soopgwan.domain.user.application.UserService;
import com.example.soopgwan.domain.user.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class HabitService {
    private final WeekHabitRepository weekHabitRepository;
    private final UserService userService;

    @Transactional
    public void createHabit(CreateHabitRequest request) {
        User user = userService.getCurrentUser();
        WeekHabit weekHabit = WeekHabit.builder()
                .content(request.getContent())
                .startAt(LocalDate.now())
                .endAt(LocalDate.now().plusDays(7))
                .status(0)
                .user(user)
                .build();

        weekHabitRepository.save(weekHabit);
    }
}
