package com.example.loginpractice.service;

import com.example.loginpractice.payload.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    void exceute(String email);
}
