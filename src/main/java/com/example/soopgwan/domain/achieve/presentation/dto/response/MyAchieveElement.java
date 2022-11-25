package com.example.soopgwan.domain.achieve.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyAchieveElement {
    private final String title;
    private final String rarityType;
    private final String productType;
    private final String content;
}
