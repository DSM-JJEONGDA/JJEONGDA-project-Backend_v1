package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException(){
        super(ErrorCode.POST_NOT_FOUND);
    }
}

