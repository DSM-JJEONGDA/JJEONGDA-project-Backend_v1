package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class SendMessageFailedException extends BusinessException {

    public SendMessageFailedException() {
        super(ErrorCode.SEND_MESSAGE_FAILED);
    }
}
