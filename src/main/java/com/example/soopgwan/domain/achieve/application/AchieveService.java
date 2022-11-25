package com.example.soopgwan.domain.achieve.application;

import com.example.soopgwan.domain.achieve.achieves.BaseAchieve;
import com.example.soopgwan.domain.achieve.achieves.utils.AchieveUtils;
import com.example.soopgwan.domain.achieve.persistence.enums.AchieveType;
import com.example.soopgwan.domain.achieve.persistence.Achieve;
import com.example.soopgwan.domain.achieve.persistence.AchieveSuccess;
import com.example.soopgwan.domain.achieve.persistence.repository.AchieveRepository;
import com.example.soopgwan.domain.achieve.persistence.repository.AchieveSuccessRepository;
import com.example.soopgwan.domain.achieve.persistence.repository.vo.DefaultAchieveVO;
import com.example.soopgwan.domain.achieve.presentation.dto.response.MyAchieveElement;
import com.example.soopgwan.domain.achieve.presentation.dto.response.MyAchieveListResponse;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AchieveService {
    private final UserUtil userUtil;
    private final AchieveSuccessRepository achieveSuccessRepository;
    private final AchieveRepository achieveRepository;
    private final WeekHabitRepository weekHabitRepository;
    private final AchieveUtils achieveUtils;

    @Transactional
    public MyAchieveListResponse execute() {
        User user = userUtil.getCurrentUser();

        List<DefaultAchieveVO> claimAchieveList = achieveRepository.findAllByAchieveCollectionsNotIn(user.getId());

        for (DefaultAchieveVO vo : claimAchieveList) {
            BaseAchieve baseAchieve = AchieveType.getAchieve(
                    weekHabitRepository, userUtil, vo.getCode(), achieveUtils
            );
            Achieve achieve = baseAchieve.getAchieveEntity();
            if (baseAchieve.isGoalSuccess()) {
                achieveSuccessRepository.save(
                        AchieveSuccess.builder()
                                .achieve(achieve)
                                .user(user)
                                .build()
                );
            }
        }

        List<MyAchieveElement> response = achieveSuccessRepository.findAllByUserId(user.getId())
                .stream()
                .map(AchieveSuccess::getAchieve)
                .map(achieve -> MyAchieveElement.builder()
                        .title(achieve.getTitle())
                        .productType(achieve.getProductType())
                        .rarityType(achieve.getRarityType())
                        .content(achieve.getContent())
                        .build())
                .toList();

        return new MyAchieveListResponse(response);
    }
}
