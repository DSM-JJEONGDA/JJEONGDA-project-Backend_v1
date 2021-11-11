package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class EmailNotCertifiedException extends BusinessException {

    public static BusinessException EXCEPTION =
            new EmailNotCorrectException();

    public EmailNotCorrectException(){
        super(ErrorCode.EMAIL_NOT_CORRECT);
    }
}
