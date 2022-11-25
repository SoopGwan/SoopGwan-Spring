package com.example.soopgwan.domain.achieve.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyAchieveListResponse {
    private final List<MyAchieveElement> myAchieveList;
}
