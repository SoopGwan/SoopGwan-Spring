package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.HabitSuccess;
import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitSuccessRepository extends JpaRepository<HabitSuccess, Long> {

    Optional<HabitSuccess> findByWeekHabit(WeekHabit weekHabit);
}
