package com.example.loginpractice.payload;

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

    @Size(min = 8,max = 12, message = "비밀번호는 최소 8글자 이상 최대 12글자 이하여야 합니다.")
    private String password;

    @Size(min = 1,max = 10, message = "이름은 최소 1글자 이상 최대 10글자 이하여야 합니다.")
    private String name;
}
