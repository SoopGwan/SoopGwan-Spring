package com.example.soopgwan.domain.habit.persistence.repository;

import com.example.soopgwan.domain.habit.persistence.repository.vo.QWeekHabitVO;
import com.example.soopgwan.domain.habit.persistence.repository.vo.WeekHabitVO;
import com.example.soopgwan.domain.user.persistence.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.example.soopgwan.domain.habit.persistence.QWeekHabit.weekHabit;

@RequiredArgsConstructor
public class WeekHabitRepositoryCustomImpl implements WeekHabitRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<WeekHabitVO> getAllWeekHabit(User user, LocalDate date) {
        LocalDate now = LocalDate.now();

        return queryFactory
                .select(
                        new QWeekHabitVO(
                                weekHabit.startAt, weekHabit.endAt
                        )
                )
                .from(weekHabit)
                .where(
                        weekHabit.user.eq(user),
                        weekHabit.endAt.between(date, now)
                )
                .groupBy(weekHabit.startAt, weekHabit.endAt)
                .orderBy(weekHabit.startAt.desc())
                .fetch();
    }
}
