package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
