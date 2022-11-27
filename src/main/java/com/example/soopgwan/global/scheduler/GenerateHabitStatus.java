package com.example.soopgwan.global.scheduler;

import com.example.soopgwan.domain.habit.application.enums.Date;
import com.example.soopgwan.domain.habit.persistence.WeekHabitStatus;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitStatusRepository;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.global.util.CalenderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GenerateHabitStatus {

    private final CalenderUtil calenderUtil;
    private final UserRepository userRepository;
    private final WeekHabitStatusRepository weekHabitStatusRepository;

    @Scheduled(cron = "0 0 1 * 0")
    public void generateWeekHabitStatus() {
        LocalDate startAt = calenderUtil.getStartAtAndEndAt(Date.START_AT);
        LocalDate endAt = calenderUtil.getStartAtAndEndAt(Date.END_AT);

        List<WeekHabitStatus> weekHabitStatusList = userRepository.findAllUserNotIn()
                .stream()
                .map(user -> WeekHabitStatus.builder()
                        .startAt(startAt)
                        .endAt(endAt)
                        .status(0)
                        .user(user)
                        .build())
                .toList();
        weekHabitStatusRepository.saveAll(weekHabitStatusList);
    }
}
