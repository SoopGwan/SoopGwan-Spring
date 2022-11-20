package com.example.soopgwan.domain.habit.application;

import com.example.soopgwan.domain.habit.application.enums.Date;
import com.example.soopgwan.domain.habit.exception.HabitNotFound;
import com.example.soopgwan.domain.habit.exception.UserAccessForbidden;
import com.example.soopgwan.domain.habit.persistence.HabitSuccess;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.habit.persistence.repository.HabitSuccessRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.habit.presentation.dto.request.CheckWeekHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.request.CreateHabitRequest;
import com.example.soopgwan.domain.habit.presentation.dto.response.ArchiveWeekHabitElement;
import com.example.soopgwan.domain.habit.presentation.dto.response.GetArchiveWeekHabitResponse;
import com.example.soopgwan.domain.habit.presentation.dto.response.GetWeekHabitResponse;
import com.example.soopgwan.domain.habit.presentation.dto.response.HabitElement;
import com.example.soopgwan.domain.habit.presentation.dto.response.HabitResponse;
import com.example.soopgwan.domain.habit.presentation.dto.response.WeekHabitElement;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    public void deleteHabit(Long habitId) {
        User user = userUtil.getCurrentUser();

        WeekHabit weekHabit = weekHabitRepository.findById(habitId)
                .orElseThrow(() -> HabitNotFound.EXCEPTION);

        if (!user.equals(weekHabit.getUser())) {
            throw UserAccessForbidden.EXCEPTION;
        }

        weekHabitRepository.delete(weekHabit);
    }

    @Transactional
    public void checkHabitSuccess(Long habitId) {
        User user = userUtil.getCurrentUser();

        WeekHabit weekHabit = weekHabitRepository.findById(habitId)
                .orElseThrow(() -> HabitNotFound.EXCEPTION);

        if (!user.equals(weekHabit.getUser())) {
            throw UserAccessForbidden.EXCEPTION;
        }

        LocalDate date = LocalDate.now();

        Boolean existsByWeekHabitAndSuccessAt = habitSuccessRepository.existsByWeekHabitAndSuccessAt(weekHabit, date);

        if (Boolean.FALSE.equals(existsByWeekHabitAndSuccessAt)) {

            HabitSuccess habitSuccess = HabitSuccess.builder()
                    .successAt(date)
                    .weekHabit(weekHabit)
                    .build();

            habitSuccessRepository.save(habitSuccess);
        }
    }

    @Transactional
    public void checkWeekHabit(CheckWeekHabitRequest request) {
        User user = userUtil.getCurrentUser();

        List<WeekHabit> weekHabitList = weekHabitRepository.findAllByUserAndStartAtBetween(
                        user,
                        getStartAtAndEndAt(Date.START_AT),
                        getStartAtAndEndAt(Date.END_AT)
                )
                .stream()
                .map(weekHabit -> weekHabit.setStatus(request.getStatus()))
                .toList();
        weekHabitRepository.saveAll(weekHabitList);
    }

    @Transactional(readOnly = true)
    public GetWeekHabitResponse getWeekHabit() {
        User user = userUtil.getCurrentUser();

        List<WeekHabitElement> weekHabits = weekHabitRepository.findAllByUserAndStartAtBetween(
                        user,
                        getStartAtAndEndAt(Date.START_AT),
                        getStartAtAndEndAt(Date.END_AT)
                )
                .stream()
                .map(weekHabit -> {
                            Integer successCount = habitSuccessRepository
                                    .countByWeekHabitAndSuccessAt(weekHabit, LocalDate.now());
                            Boolean status = successCount == 0 ? Boolean.FALSE : Boolean.TRUE;

                            return WeekHabitElement.builder()
                                    .id(weekHabit.getId())
                                    .content(weekHabit.getContent())
                                    .successCount(successCount)
                                    .successStatus(status)
                                    .build();
                        }
                ).toList();

        return new GetWeekHabitResponse(weekHabits);
    }

    @Transactional(readOnly = true)
    public HabitResponse getAllHabit() {
        User user = userUtil.getCurrentUser();

        List<HabitElement> habitList = weekHabitRepository.findAllByUser(user)
                .stream()
                .map(weekHabit -> HabitElement.builder()
                        .id(weekHabit.getId())
                        .startAt(weekHabit.getStartAt())
                        .endAt(weekHabit.getEndAt())
                        .level(weekHabit.getStatus())
                        .build())
                .toList();

        return new HabitResponse(habitList);
    }

    @Transactional(readOnly = true)
    public GetArchiveWeekHabitResponse getArchiveWeekHabit(LocalDate startAt, LocalDate endAt) {
        User user = userUtil.getCurrentUser();

        List<ArchiveWeekHabitElement> archiveWeekHabits = weekHabitRepository.findAllByUserAndStartAtBetween(
                        user, startAt, endAt
                )
                .stream()
                .map(weekHabit -> ArchiveWeekHabitElement.builder()
                        .title(weekHabit.getContent())
                        .count(habitSuccessRepository.countHabitSuccessByWeekHabit(weekHabit))
                        .build())
                .toList();

        return new GetArchiveWeekHabitResponse(archiveWeekHabits);
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
