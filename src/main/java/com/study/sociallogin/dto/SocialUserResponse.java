package com.study.sociallogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SocialUserResponse {
    private String id;
    private String email;
    private String name;
    private String gender;
    private String birthday;
}
