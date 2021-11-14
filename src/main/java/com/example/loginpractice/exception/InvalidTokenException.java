package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}
