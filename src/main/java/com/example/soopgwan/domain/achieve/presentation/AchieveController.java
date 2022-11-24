package com.example.soopgwan.domain.achieve.presentation;

import com.example.soopgwan.domain.achieve.application.AchieveService;
import com.example.soopgwan.domain.achieve.presentation.dto.response.MyAchieveLIstResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/achieve")
@RestController
@RequiredArgsConstructor
public class AchieveController {

    private final AchieveService achieveService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MyAchieveLIstResponse getAchieve() {
        return achieveService.execute();
    }
}
