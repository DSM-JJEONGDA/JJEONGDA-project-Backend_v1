package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class ExpiredRefreshTokenException extends BusinessException {
    public static BusinessException EXCEPTION =
            new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException(){
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
