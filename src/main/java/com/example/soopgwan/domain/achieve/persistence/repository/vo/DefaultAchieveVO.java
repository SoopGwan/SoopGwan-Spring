package com.example.soopgwan.domain.achieve.persistence.repository.vo;

import com.example.soopgwan.domain.achieve.persistence.enums.AchieveType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DefaultAchieveVO {
    private final Long achieveId;
    private final String content;
    private final String rarityType;
    private final String productType;
    private final String title;
    private final AchieveType code;

    @QueryProjection
    public DefaultAchieveVO(Long achieveId, AchieveType code, String rarityType, String productType, String title, String content) {
        this.achieveId = achieveId;
        this.code = code;
        this.rarityType = rarityType;
        this.productType = productType;
        this.title = title;
        this.content = content;
    }
}
