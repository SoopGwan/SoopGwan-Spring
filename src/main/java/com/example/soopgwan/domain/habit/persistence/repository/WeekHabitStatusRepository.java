package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.WeekHabitStatus;
import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeekHabitStatusRepository extends JpaRepository<WeekHabitStatus, Long> {
    Optional<WeekHabitStatus> findByUserAndStartAtBetween(User user, LocalDate startAt, LocalDate endAt);

    Integer countByUserAndStartAtBetween(User user, LocalDate startAt, LocalDate endAt);

    boolean existsByUserAndStartAtBetween(User user, LocalDate startAt, LocalDate endAt);
}
