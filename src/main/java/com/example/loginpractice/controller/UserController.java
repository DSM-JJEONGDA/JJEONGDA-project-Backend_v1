package com.example.loginpractice.controller;

import com.example.loginpractice.entity.refreshToken.RefreshTokenRepository;
import com.example.loginpractice.entity.user.User;
import com.example.loginpractice.entity.user.UserRepository;
import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.security.jwt.JwtTokenProvider;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/sendEmail")
    public void sendEmail(@Valid @RequestBody EmailRequest request){
        authService.sendEmail(request);
    }

    @PostMapping("/email")
    public void verifyAccount(@Valid @RequestBody EmailVerifiedRequest request){
        authService.verifyAccount(request);
    }

    //회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createJwtAccessToken(member.getUsername());
    }
}