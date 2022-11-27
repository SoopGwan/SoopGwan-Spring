package com.example.soopgwan.domain.habit.application;

import com.example.soopgwan.domain.habit.application.enums.Date;
import com.example.soopgwan.domain.habit.exception.AlreadyCheck;
import com.example.soopgwan.domain.habit.exception.ExistsHabitStatus;
import com.example.soopgwan.domain.habit.exception.HabitNotFound;
import com.example.soopgwan.domain.habit.exception.UserAccessForbidden;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.habit.persistence.WeekHabitStatus;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitStatusRepository;
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
    private final WeekHabitStatusRepository weekHabitStatusRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    @Transactional
    public void createHabit(CreateHabitRequest request) {
        User user = userUtil.getCurrentUser();

        WeekHabit weekHabit = WeekHabit.builder()
                .content(request.getContent())
                .startAt(getStartAtAndEndAt(Date.START_AT))
                .endAt(getStartAtAndEndAt(Date.END_AT))
                .successCount(0)
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

        if (LocalDate.now().equals(weekHabit.getLastSuccessAt())) {
            throw AlreadyCheck.EXCEPTION;
        }

        weekHabit.check();
    }

    @Transactional
    public void checkWeekHabit(CheckWeekHabitRequest request) {
        User user = userUtil.getCurrentUser();
        LocalDate startAt = getStartAtAndEndAt(Date.START_AT);
        LocalDate endAt = getStartAtAndEndAt(Date.END_AT);

        if (weekHabitStatusRepository.existsByUserAndStartAtAndEndAt(user, startAt, endAt)) {
            throw ExistsHabitStatus.EXCEPTION;
        }

        WeekHabitStatus status = WeekHabitStatus.builder()
                .startAt(startAt)
                .endAt(endAt)
                .status(request.getStatus())
                .user(user)
                .build();
        weekHabitStatusRepository.save(status);
    }

    @Transactional(readOnly = true)
    public GetWeekHabitResponse getWeekHabit() {
        User user = userUtil.getCurrentUser();

        List<WeekHabitElement> weekHabits = weekHabitRepository.findAllByUserAndStartAtAndEndAt(
                        user, getStartAtAndEndAt(Date.START_AT), getStartAtAndEndAt(Date.END_AT)
                )
                .stream()
                .map(weekHabit -> {
                            Integer successCount = weekHabit.getSuccessCount();
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
    public HabitResponse getAllHabit(LocalDate date) {
        User user = userUtil.getCurrentUser();

        List<HabitElement> habitList = weekHabitRepository.getAllWeekHabit(user, date)
                .stream()
                .map(weekHabitVO -> HabitElement.builder()
                        .startAt(weekHabitVO.getStartAt())
                        .endAt(weekHabitVO.getEndAt())
                        .level(getLevel(weekHabitVO.getSuccessCount()))
                        .build())
                .toList();

        return new HabitResponse(habitList);
    }

    private Integer getLevel(Long successCount) {
        int result = 0;

        if (successCount >= 1 && successCount <= 4) {
            result = 1;
        } else if (successCount >= 5 && successCount <= 10) {
            result = 2;
        } else if (successCount >= 11 && successCount <= 20) {
            result = 3;
        } else if (successCount >= 21 && successCount <= 35) {
            result = 4;
        }

        return result;
    }

    @Transactional(readOnly = true)
    public GetArchiveWeekHabitResponse getArchiveWeekHabit(LocalDate startAt, LocalDate endAt) {
        User user = userUtil.getCurrentUser();

        WeekHabitStatus status = weekHabitStatusRepository.findByUserAndStartAtAndEndAt(user, startAt, endAt)
                .orElseGet(() -> WeekHabitStatus.builder().status(0).build());

        List<ArchiveWeekHabitElement> archiveWeekHabits = weekHabitRepository.findAllByUserAndStartAtAndEndAt(
                        user, startAt, endAt
                )
                .stream()
                .map(weekHabit -> ArchiveWeekHabitElement.builder()
                        .title(weekHabit.getContent())
                        .count(weekHabit.getSuccessCount())
                        .build())
                .toList();

        return new GetArchiveWeekHabitResponse(archiveWeekHabits, status.getStatus());
    }

    private LocalDate getStartAtAndEndAt(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        if (Date.END_AT.equals(date)) {
            calendar.add(Calendar.DATE, 6);
        }

        String dateFormat = formatter.format(calendar.getTime()).replace(".", "-");

        return LocalDate.parse(dateFormat);
    }
}
