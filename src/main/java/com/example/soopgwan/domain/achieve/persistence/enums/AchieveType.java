package com.example.soopgwan.domain.achieve.persistence.enums;

import com.example.soopgwan.domain.achieve.achieves.*;
import com.example.soopgwan.domain.achieve.exception.AchieveTypeNotFoundException;
import com.example.soopgwan.domain.achieve.persistence.repository.AchieveRepository;
import com.example.soopgwan.domain.habit.persistence.repository.WeekHabitRepository;
import com.example.soopgwan.global.util.UserUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AchieveType {

    CREATE_1("create_1"),
    CREATE_5("create_5"),
    CREATE_15("create_15"),
    CREATE_20("create_20"),
    COMPLETE_5("complete_5"),
    COMPLETE_10("complete_10"),
    COMPLETE_15("complete_15"),
    COMPLETE_25("complete_25");

    private final String code;

    public static BaseAchieve getAchieve(WeekHabitRepository weekHabitRepository, UserUtil userUtil,
                                         AchieveType achieveType, AchieveRepository achieveRepository) {
        return switch (achieveType) {
            case CREATE_1 -> new Create1Achieve(weekHabitRepository, achieveRepository, userUtil);
            case CREATE_5 -> new Create5Achieve(weekHabitRepository, achieveRepository, userUtil);
            case CREATE_15 -> new Create15Achieve(weekHabitRepository, achieveRepository, userUtil);
            case CREATE_20 -> new Create20Ahieve(weekHabitRepository, achieveRepository, userUtil);
            case COMPLETE_5 -> new Complete5Achieve(weekHabitRepository, achieveRepository, userUtil);
            case COMPLETE_10 -> new Complete10Achieve(weekHabitRepository, achieveRepository, userUtil);
            case COMPLETE_15 -> new Complete15Achieve(weekHabitRepository, achieveRepository, userUtil);
            case COMPLETE_25 -> new Complete25Achieve(weekHabitRepository, achieveRepository, userUtil);
            default -> throw AchieveTypeNotFoundException.EXCEPTION;
        };
    }

}
