package com.example.soopgwan.domain.achieve.achieves;

import com.example.soopgwan.domain.achieve.exception.AchieveNotFoundException;
import com.example.soopgwan.domain.achieve.persistence.Achieve;
import com.example.soopgwan.domain.achieve.persistence.enums.AchieveType;
import com.example.soopgwan.domain.achieve.persistence.repository.AchieveRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Complete10Achieve  implements BaseAchieve {

    private final WeekHabitRepository weekHabitRepository;
    private final AchieveRepository achieveRepository;
    private final UserUtil userUtil;

    @Override
    public Achieve getAchieveEntity() {
        return achieveRepository.findByCode(AchieveType.COMPLETE_10)
                .orElseThrow(() -> AchieveNotFoundException.EXCEPTION);
    }

    @Override
    public boolean isGoalSuccess() {
        User user = userUtil.getCurrentUser();

        return weekHabitRepository.sumSuccessCountAllByUser(user) >= 10;
    }
}