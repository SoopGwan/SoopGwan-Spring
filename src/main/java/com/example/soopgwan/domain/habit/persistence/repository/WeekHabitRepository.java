package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.WeekHabit;
import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekHabitRepository extends JpaRepository<WeekHabit, Long>, WeekHabitRepositoryCustom {

    List<WeekHabit> findAllByUserAndStartAtAndEndAt(User user, LocalDate startAt, LocalDate endAt);

    @Query("SELECT SUM(w.successCount) FROM WeekHabit w WHERE w.user = :user")
    Long sumSuccessCountAllByUser(User user);

    Long countAllByUser(User user);
}
