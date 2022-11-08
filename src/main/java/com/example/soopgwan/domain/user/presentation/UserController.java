package com.example.soopgwan.domain.user.presentation;

import com.example.soopgwan.domain.user.application.UserService;
import com.example.soopgwan.domain.user.presentation.dto.request.SignUpRequset;
import com.example.soopgwan.domain.user.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService signUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequset request) {
        return signUpService.execute(request);
    }
}