package com.study.sociallogin.dto;

import com.study.sociallogin.type.UserType;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Valid
public class SocialLoginRequest {
    @NotNull
    private UserType userType;
    @NotNull
    private String code;
}
