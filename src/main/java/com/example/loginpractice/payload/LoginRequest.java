package com.example.loginpractice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email
    private String email;

    private String password;
}
