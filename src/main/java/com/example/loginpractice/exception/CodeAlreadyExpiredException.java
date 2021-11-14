package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class CodeAlreadyExpiredException extends BusinessException {
    public CodeAlreadyExpiredException(){
        super(ErrorCode.CODE_ALREADY_EXPIRED);
    }
}
