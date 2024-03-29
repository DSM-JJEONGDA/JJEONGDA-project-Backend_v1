package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class ExpiredAccessTokenException extends BusinessException {
    public static BusinessException EXCEPTION =
            new ExpiredAccessTokenException();

    private ExpiredAccessTokenException(){
        super(ErrorCode.EXPIRED_ACCESS_TOKEN);
    }
}
