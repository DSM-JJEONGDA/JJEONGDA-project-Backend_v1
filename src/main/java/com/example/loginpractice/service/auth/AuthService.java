package com.example.loginpractice.service.auth;

import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.payload.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
}
