package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class UserNameAlreadyExistsException extends BusinessException {
    public UserNameAlreadyExistsException(){
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
