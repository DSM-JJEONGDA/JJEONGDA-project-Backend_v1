package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class IncorrectTokenException extends BusinessException {
    public IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}
