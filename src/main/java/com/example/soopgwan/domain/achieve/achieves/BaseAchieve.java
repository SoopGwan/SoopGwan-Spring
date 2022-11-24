package com.example.soopgwan.domain.achieve.achieves;

import com.example.soopgwan.domain.achieve.persistence.Achieve;

public interface BaseAchieve {
    boolean isGoalSuccess();

    Achieve getAchieveEntity();
}
