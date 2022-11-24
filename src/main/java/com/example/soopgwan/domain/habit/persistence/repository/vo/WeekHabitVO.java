package com.example.soopgwan.domain.habit.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WeekHabitVO {

    private final LocalDate startAt;
    private final LocalDate endAt;

    @QueryProjection
    public WeekHabitVO(LocalDate startAt, LocalDate endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
