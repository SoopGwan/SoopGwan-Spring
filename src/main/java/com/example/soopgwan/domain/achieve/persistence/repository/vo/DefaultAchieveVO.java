package com.example.soopgwan.domain.achieve.persistence.repository.vo;

import com.example.soopgwan.domain.achieve.persistence.enums.AchieveType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DefaultAchieveVO {
    private final Long achieveId;
    private final AchieveType code;
    private final String rarityType;
    private final String productType;

    @QueryProjection
    public DefaultAchieveVO(Long achieveId, AchieveType code, String rarityType, String productType) {
        this.achieveId = achieveId;
        this.code = code;
        this.rarityType = rarityType;
        this.productType = productType;
    }
}
