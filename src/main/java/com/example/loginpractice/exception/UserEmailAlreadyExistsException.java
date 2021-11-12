package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class UserEmailAlreadyExistsException extends BusinessException {
    public UserEmailAlreadyExistsException(){
        super(ErrorCode.MEMBER_EMAIL_ALREADY_EXISTS);
    }
}
