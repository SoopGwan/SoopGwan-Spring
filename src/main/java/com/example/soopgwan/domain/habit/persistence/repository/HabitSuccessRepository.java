package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.HabitSuccess;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HabitSuccessRepository extends JpaRepository<HabitSuccess, Long> {
    Boolean existsByWeekHabitAndSuccessAt(WeekHabit weekHabit, LocalDate successAt);

    Integer countByWeekHabitAndSuccessAt(WeekHabit weekHabit, LocalDate successAt);

    Long countHabitSuccessByWeekHabit(WeekHabit weekHabit);
}
