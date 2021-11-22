package com.example.loginpractice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //토큰
    INCORRECT_TOKEN(400, "Incorrect Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_ACCESS_TOKEN(401, "Expired Access Token"),
    EXPIRED_REFRESH_TOKEN(401, "Expired Refresh Token"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    //회원
    USER_NAME_ALREADY_EXISTS(409, "User Name Already Exists"),
    USER_EMAIL_ALREADY_EXISTS(409, "User Email Already Exists"),
    USER_NOT_FOUND(404, "User Not Found"),
    USER_USERNAME_ALREADY_EXISTS(409, "User Already Exists"),
    INVALID_PASSWORD(401, "Invalid Password"),

    //메일 인증
    EMAIL_NOT_CERTIFIED(401, "Email Not Certified"),
    SEND_MESSAGE_FAILED(500, "Send Message Failed"),

    CODE_NOT_CORRECT(401, "Code Not Correct"),
    CODE_ALREADY_EXPIRED(401, "Code Already Expired");

    private final int status;
    private final String message;
}
