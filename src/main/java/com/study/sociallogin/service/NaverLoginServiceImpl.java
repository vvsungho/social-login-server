package com.study.sociallogin.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.sociallogin.dto.KaKaoLoginResponse;
import com.study.sociallogin.dto.NaverLoginResponse;
import com.study.sociallogin.dto.SocialAuthResponse;
import com.study.sociallogin.dto.SocialUserResponse;
import com.study.sociallogin.feign.naver.NaverAuthApi;
import com.study.sociallogin.feign.naver.NaverUserApi;
import com.study.sociallogin.type.UserType;
import com.study.sociallogin.utils.GsonLocalDateTimeAdapter;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("naverLogin")
public class NaverLoginServiceImpl implements SocialLoginService {

  private final NaverAuthApi naverAuthApi;
  private final NaverUserApi naverUserApi;

  @Value("${social.client.naver.rest-api-key}")
  private String naverAppKey;
  @Value("${social.client.naver.secret-key}")
  private String naverAppSecret;
  @Value("${social.client.naver.redirect-uri}")
  private String naverRedirectUri;
  @Value("${social.client.naver.grant_type}")
  private String naverGrantType;


  @Override
  public UserType getServiceName() {
    return UserType.NAVER;
  }

  @Override
  public SocialAuthResponse getAccessToken(String authorizationCode) {
    ResponseEntity<?> response = naverAuthApi.getAccessToken(
        naverGrantType,
        naverAppKey,
        naverAppSecret,
        authorizationCode,
        "state"
    );

    log.info("naver auth response {}", response.toString());

    return new Gson()
        .fromJson(
            String.valueOf(response.getBody())
            , SocialAuthResponse.class
        );
  }

  @Override
  public SocialUserResponse getUserInfo(String accessToken) {
    Map<String ,String> headerMap = new HashMap<>();
    headerMap.put("authorization", "Bearer " + accessToken);

    ResponseEntity<?> response = naverUserApi.getUserInfo(headerMap);

    log.info("naver user response");
    log.info(response.toString());

    String jsonString = response.getBody().toString();

    Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
        .create();

    NaverLoginResponse naverLoginResponse = gson.fromJson(jsonString, NaverLoginResponse.class);
    NaverLoginResponse.Response naverUserInfo = naverLoginResponse.getResponse();

    return SocialUserResponse.builder()
        .id(naverUserInfo.getId())
        .gender(naverUserInfo.getGender())
        .name(naverUserInfo.getName())
        .email(naverUserInfo.getEmail())
        .build();
  }
}
