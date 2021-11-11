package com.example.loginpractice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email
    private String email;

    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하여야 합니다.")
    private String password;
}
