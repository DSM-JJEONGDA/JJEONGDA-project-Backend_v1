package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class SendMessageFailedException extends BusinessException {

    public static BusinessException EXCEPTION =
            new SendMailFailedException();

    public SendMailFailedException(){
        super(ErrorCode.SEND_FAILED_MAIL);
    }
}
