package com.example.loginpractice.controller;

import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.payload.request.LoginRequest;
import com.example.loginpractice.payload.response.TokenResponse;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    //email 전송
    @PostMapping("/send")
    public void sendEmail(@Valid @RequestBody EmailRequest request) {
        authService.sendEmail(request);
    }

    //email 인증
    @PostMapping("/email")
    public void verifyAccount(@Valid @RequestBody EmailVerifiedRequest request) {
        authService.verifyAccount(request);
    }

    //회원가입
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
    }

    //로그인
    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    //토큰 재발급
    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader(name = "X-REFRESH-TOKEN") String token) {
        return authService.reissue(token);
    }
}