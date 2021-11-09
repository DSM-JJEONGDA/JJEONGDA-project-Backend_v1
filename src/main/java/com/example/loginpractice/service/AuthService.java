package com.example.loginpractice.service;

import com.example.loginpractice.jwt.AccessTokenResponseDto;
import com.example.loginpractice.payload.LoginRequest;
import com.example.loginpractice.payload.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    AccessTokenResponseDto login(LoginRequest request);
}
