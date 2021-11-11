package com.example.loginpractice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EMAIL_NOT_CORRECT(400, "Email Not Correct"),
    CODE_NOT_CORRECT(400, "Code Not Correct"),
    SEND_FAILED_MAIL(500, "Send Message Failed"),
    ;

    private final int statusCode;
    private final String message;
}
