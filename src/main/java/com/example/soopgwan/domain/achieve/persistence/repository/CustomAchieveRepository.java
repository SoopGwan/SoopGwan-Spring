package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.persistence.repository.vo.DefaultAchieveVO;

import java.util.List;

public interface CustomAchieveRepository {
    List<DefaultAchieveVO> findAllByAchieveCollectionsNotIn(Long userId);
}
