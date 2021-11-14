package com.example.loginpractice.exception;

import com.example.loginpractice.error.ErrorCode;
import com.example.loginpractice.error.exception.BusinessException;

public class CodeNotCorrectException extends BusinessException {

    public CodeNotCorrectException(){
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
