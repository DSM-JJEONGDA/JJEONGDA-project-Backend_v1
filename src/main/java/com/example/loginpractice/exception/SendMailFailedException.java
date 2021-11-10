package com.example.loginpractice.exception;

import com.example.loginpractice.security.ApiException;
import com.example.loginpractice.security.ErrorCode;

public class SendMailFailedException extends ApiException {

    public static ApiException EXCEPTION =
            new SendMailFailedException();

    private SendMailFailedException(){
        super(ErrorCode.SEND_FAILED_MAIL);
    }
}
