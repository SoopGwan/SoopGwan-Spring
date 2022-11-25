package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.persistence.repository.vo.DefaultAchieveVO;
import com.example.soopgwan.domain.achieve.persistence.repository.vo.QDefaultAchieveVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.soopgwan.domain.achieve.persistence.QAchieve.achieve;
import static com.example.soopgwan.domain.achieve.persistence.QAchieveSuccess.achieveSuccess;
import static com.example.soopgwan.domain.user.persistence.QUser.user;

@RequiredArgsConstructor
public class CustomAchieveRepositoryImpl implements CustomAchieveRepository {

    private final JPAQueryFactory query;

    @Override
    public List<DefaultAchieveVO> findAllByAchieveCollectionsNotIn(Long userId) {
        return query
                .select(new QDefaultAchieveVO(
                        achieve.id,
                        achieve.code,
                        achieve.rarityType,
                        achieve.productType,
                        achieve.title,
                        achieve.content
                        ))
                .from(achieve)
                .leftJoin(achieveSuccess)
                .on(achieveSuccess.achieve.eq(achieve))
                .leftJoin(user)
                .on(achieveSuccess.user.eq(user), user.id.eq(userId))
                .where(user.id.isNull())
                .fetch();
    }
}
