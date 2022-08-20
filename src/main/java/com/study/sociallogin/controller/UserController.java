package com.study.sociallogin.controller;

import com.study.sociallogin.dto.SocialLoginRequest;
import com.study.sociallogin.dto.LoginResponse;
import com.study.sociallogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService loginService;

    @PostMapping("/social-login")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody SocialLoginRequest request) {
        loginService.doSocialLogin(request);

        return ResponseEntity.created(URI.create("/social-login")).body(LoginResponse.builder().build());
    }
}
