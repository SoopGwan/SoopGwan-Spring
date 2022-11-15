package com.example.soopgwan.domain.user.presentation.dto.request;

import com.example.soopgwan.global.error.CustomException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;
}
