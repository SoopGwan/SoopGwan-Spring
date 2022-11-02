package com.example.soopgwan.domain.habit.application;

import com.example.soopgwan.domain.habit.exception.HabitNotFound;
import com.example.soopgwan.domain.habit.persistence.HabitSuccess;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.habit.persistence.repository.HabitSuccessRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class HabitService {

    private final WeekHabitRepository weekHabitRepository;
    private final UserUtil userUtil;
    private final HabitSuccessRepository habitSuccessRepository;

    public void createHabit(CreateHabitRequest request) {
        User user = userUtil.getCurrentUser();

        WeekHabit weekHabit = WeekHabit.builder()
                .content(request.getContent())
                // TODO 날짜 다시 지정하기
                .startAt(LocalDate.now())
                .endAt(LocalDate.now().plusDays(7))
                .user(user)
                .build();

        weekHabitRepository.save(weekHabit);
    }

    public void deleteHabit(Long habitId) {
        WeekHabit weekHabit = weekHabitRepository.findById(habitId)
                .orElseThrow(() -> HabitNotFound.EXCEPTION);

        weekHabitRepository.delete(weekHabit);
    }

    public void checkHabitSuccess(Long habitId) {
        WeekHabit weekHabit = weekHabitRepository.findById(habitId)
                .orElseThrow(() -> HabitNotFound.EXCEPTION);

        HabitSuccess habitSuccess = habitSuccessRepository.findByWeekHabit(weekHabit)
                .orElseGet(() ->
                        HabitSuccess.builder()
                                .count(0)
                                .successAt(LocalDate.now())
                                .weekHabit(weekHabit)
                                .build()
                );

        habitSuccessRepository.save(
                habitSuccess.plusCount()
        );
    }
}
