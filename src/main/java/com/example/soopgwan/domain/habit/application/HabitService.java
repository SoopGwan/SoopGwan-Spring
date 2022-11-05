package com.example.soopgwan.domain.habit.application;

import com.example.soopgwan.domain.habit.application.enums.Date;
import com.example.soopgwan.domain.habit.exception.HabitNotFound;
import com.example.soopgwan.domain.habit.persistence.HabitSuccess;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.habit.persistence.repository.HabitSuccessRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.habit.presentation.dto.request.CheckWeekHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.response.ReferWeekHabitResponse;
import com.example.soopgwan.domain.habit.presentation.dto.response.WeekHabitElement;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HabitService {

    private final WeekHabitRepository weekHabitRepository;
    private final UserUtil userUtil;
    private final HabitSuccessRepository habitSuccessRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    public void createHabit(CreateHabitRequest request) {
        User user = userUtil.getCurrentUser();

        WeekHabit weekHabit = WeekHabit.builder()
                .content(request.getContent())
                .startAt(getStartAtAndEndAt(Date.START_AT))
                .endAt(getStartAtAndEndAt(Date.END_AT))
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

        LocalDate date = LocalDate.now();

        Boolean alreadyExists = habitSuccessRepository.existsByWeekHabitAndSuccessAt(weekHabit, date);

        if (alreadyExists) {
            return;
        }

        HabitSuccess habitSuccess = HabitSuccess.builder()
                .successAt(date)
                .weekHabit(weekHabit)
                .build();

        habitSuccessRepository.save(habitSuccess);
    }

    public void checkWeekHabit(CheckWeekHabitRequest request) {
        List<WeekHabit> weekHabitList = weekHabitRepository.findAllByStartAtBetween(
                        getStartAtAndEndAt(Date.START_AT),
                        getStartAtAndEndAt(Date.END_AT)
                )
                .stream()
                .map(weekHabit -> weekHabit.setStatus(request.getStatus()))
                .toList();

        weekHabitRepository.saveAll(weekHabitList);
    }

    public ReferWeekHabitResponse referWeekHabitResponse() {
        List<WeekHabitElement> weekHabitElementList = weekHabitRepository.findAllByStartAtBetween(getStartAtAndEndAt(Date.START_AT), getStartAtAndEndAt(Date.END_AT))
                .stream()
                .map(weekHabit -> {
                    Boolean status = habitSuccessRepository.existsByWeekHabitAndSuccessAt(weekHabit, LocalDate.now());
                    return new WeekHabitElement(weekHabit.getId(), weekHabit.getContent(), status);
                })
                .toList();

        return new ReferWeekHabitResponse(weekHabitElementList);
    }

    private LocalDate getStartAtAndEndAt(Date date) {
        Calendar calendar = Calendar.getInstance();

        if (Date.START_AT.equals(date)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.add(Calendar.DATE, 7);
        }

        String dateFormat = formatter.format(calendar.getTime()).replace(".", "-");

        return LocalDate.parse(dateFormat);
    }
}
