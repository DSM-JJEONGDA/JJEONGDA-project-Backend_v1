package com.example.loginpractice.service;

import com.example.loginpractice.entity.User;
import com.example.loginpractice.entity.UserRepository;
import com.example.loginpractice.exception.AlreadyUserExistException;
import com.example.loginpractice.payload.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        try {
            userRepository.save(
                    User.builder()
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .build()
            );
            return "회원가입 성공";
        } catch (Exception e) {
            throw new AlreadyUserExistException();
        }
    }
}
