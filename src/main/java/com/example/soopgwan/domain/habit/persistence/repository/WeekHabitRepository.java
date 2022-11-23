package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekHabitRepository extends JpaRepository<WeekHabit, Long>, WeekHabitRepositoryCustom {

    List<WeekHabit> findAllByUserAndStartAtBetween(User user, LocalDate startAt, LocalDate endAt);

}
