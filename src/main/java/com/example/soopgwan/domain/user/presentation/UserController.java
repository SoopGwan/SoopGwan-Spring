package com.example.soopgwan.domain.user.presentation;

import com.example.soopgwan.domain.user.application.UserService;
import com.example.soopgwan.domain.user.presentation.dto.request.LoginRequest;
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
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequset request) {
        return userService.signUp(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/check", method = RequestMethod.HEAD)
    public void check(@RequestParam(value = "account_id") String accountId) {
        userService.overLapCheck(accountId);
    }
}