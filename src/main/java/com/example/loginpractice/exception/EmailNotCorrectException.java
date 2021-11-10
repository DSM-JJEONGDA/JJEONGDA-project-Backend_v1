package com.example.loginpractice.exception;

import com.example.loginpractice.security.ApiException;
import com.example.loginpractice.security.ErrorCode;

public class EmailNotCorrectException extends ApiException {

    public static ApiException EXCEPTION =
            new EmailNotCorrectException();

    public EmailNotCorrectException(){
        super(ErrorCode.EMAIL_NOT_CORRECT);
    }
}
