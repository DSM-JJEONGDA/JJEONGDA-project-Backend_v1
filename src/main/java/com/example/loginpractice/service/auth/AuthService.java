package com.example.loginpractice.service.auth;

import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.payload.request.LoginRequest;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.payload.response.TokenResponse;

public interface AuthService {

    void register(RegisterRequest request);
    //void sendEmail(EmailRequest request);
    //void verifyAccount(EmailVerifiedRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse reissue(String token);
}
