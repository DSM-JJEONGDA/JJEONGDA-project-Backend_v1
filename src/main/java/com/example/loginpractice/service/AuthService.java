package com.example.loginpractice.service;

import com.example.loginpractice.payload.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}
