package com.study.sociallogin.dto;

import com.study.sociallogin.type.UserType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SocialLoginRequest {
    @NotNull
    private UserType userType;
    @NotNull
    private String code;
}
