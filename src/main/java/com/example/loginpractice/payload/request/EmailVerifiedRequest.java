package com.example.loginpractice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerifiedRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
