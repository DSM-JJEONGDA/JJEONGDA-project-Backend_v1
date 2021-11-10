package com.example.loginpractice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
