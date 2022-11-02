package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekHabitRepository extends JpaRepository<WeekHabit, Long> {

    List<WeekHabit> findAllByStartAtBetween(LocalDate startAt, LocalDate endAt);
}
