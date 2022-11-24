package com.example.soopgwan.domain.achieve.persistence.repository.vo;

import com.example.soopgwan.domain.achieve.enums.AchieveType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DefaultAchieveVO {
    private final Long achieveId;
    private final String name;
    private final AchieveType code;
    private final String rarityType;
    private final String productType;

    @QueryProjection
    public DefaultAchieveVO(Long achieveId, String name, String rarityType, String productType, AchieveType code) {
        this.achieveId = achieveId;
        this.name = name;
        this.code = code;
        this.productType = productType;
        this.rarityType = rarityType;
    }
}
