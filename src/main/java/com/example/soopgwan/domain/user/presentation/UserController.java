package com.example.soopgwan.domain.user.presentation;

import com.example.soopgwan.domain.user.application.UserService;
import com.example.soopgwan.domain.user.presentation.dto.request.*;
import com.example.soopgwan.domain.user.presentation.dto.response.ResetPasswordResponse;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/check", method = RequestMethod.HEAD)
    public void check(@RequestParam(value = "account_id") String accountId) {
        userService.overLapCheck(accountId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/change")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        userService.changePassword(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/send")
    public void sendCode(@RequestBody @Valid SendCodeRequest request) {
        userService.sendCode(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/verify")
    public void verifyCode(@RequestBody @Valid VerifyCodeRequest request) {
        userService.verifyCode(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/reset")
    public ResetPasswordResponse resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        return userService.resetPassword(request);
    }
}