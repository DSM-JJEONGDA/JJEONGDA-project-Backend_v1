package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class ExpiredRefreshTokenException extends BusinessException {
    public ExpiredRefreshTokenException(){
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
