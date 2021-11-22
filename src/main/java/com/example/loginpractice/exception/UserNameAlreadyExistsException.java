package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class UserNameAlreadyExistsException extends BusinessException {
    public static BusinessException EXCEPTION =
            new UserNameAlreadyExistsException();

    private UserNameAlreadyExistsException(){
        super(ErrorCode.USER_NAME_ALREADY_EXISTS);
    }
}
