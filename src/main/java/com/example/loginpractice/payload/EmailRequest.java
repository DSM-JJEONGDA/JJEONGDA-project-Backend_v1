package com.example.loginpractice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotBlank(message = "null 이거나 공백, 값이 비어있으면 안됩니다.")
    @Email(message = "Email 형식이어야 합니다.")
    private String email;
}
