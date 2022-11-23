package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.repository.vo.WeekHabitVO;
import com.example.soopgwan.domain.user.persistence.User;

import java.time.LocalDate;
import java.util.List;

public interface WeekHabitRepositoryCustom {

    List<WeekHabitVO> getAllWeekHabit(User user);
}
