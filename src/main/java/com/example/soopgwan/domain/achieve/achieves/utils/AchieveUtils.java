package com.example.soopgwan.domain.achieve.achieves.utils;

import com.example.soopgwan.domain.achieve.exception.AchieveNotFoundException;
import com.example.soopgwan.domain.achieve.persistence.Achieve;
import com.example.soopgwan.domain.achieve.persistence.enums.AchieveType;
import com.example.soopgwan.domain.achieve.persistence.repository.AchieveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AchieveUtils {

    private final AchieveRepository achieveRepository;

    public Achieve findByCode(AchieveType code) {
        return achieveRepository.findByCode(code)
                .orElseThrow(() -> AchieveNotFoundException.EXCEPTION);
    }
}
