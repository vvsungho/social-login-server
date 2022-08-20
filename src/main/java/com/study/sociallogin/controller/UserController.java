package com.study.sociallogin.controller;

import com.study.sociallogin.dto.SocialLoginRequest;
import com.study.sociallogin.dto.LoginResponse;
import com.study.sociallogin.dto.UserResponse;
import com.study.sociallogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/social-login")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request) {

        return ResponseEntity.created(URI.create("/social-login"))
                .body(userService.doSocialLogin(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                userService.getUser(id)
        );
    }
}
