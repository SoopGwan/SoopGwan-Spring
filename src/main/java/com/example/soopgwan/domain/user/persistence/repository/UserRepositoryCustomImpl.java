package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.soopgwan.domain.habit.persistence.QWeekHabitStatus.weekHabitStatus;
import static com.example.soopgwan.domain.user.persistence.QUser.user;

@RequiredArgsConstructor
@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<User> findAllUserNotIn() {
        return query
                .selectFrom(user)
                .leftJoin(weekHabitStatus)
                .on(weekHabitStatus.user.eq(user))
                .where(weekHabitStatus.user.isNull())
                .fetch();
    }
}
