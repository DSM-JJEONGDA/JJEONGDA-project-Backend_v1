package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {
    public static BusinessException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException(){
        super(ErrorCode.INVALID_PASSWORD);
    }
}
