package com.example.soopgwan.domain.achieve.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MyAchieveLIstResponse {
    private final List<MyAchieve> myAchieveList;

    @Getter
    @Builder
    public static class MyAchieve {
        private final String title;
        private final String rarityType;
        private final String productType;
        private final String content;
    }
}
