package com.study.sociallogin.service;

import com.study.sociallogin.dto.SocialAuthResponse;
import com.study.sociallogin.dto.SocialUserResponse;
import com.study.sociallogin.type.UserType;
import org.springframework.stereotype.Service;

@Service
public interface SocialLoginService {
    UserType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);
}
