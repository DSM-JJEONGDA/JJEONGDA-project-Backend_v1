package com.example.loginpractice.exception;

import com.example.loginpractice.security.ApiException;
import com.example.loginpractice.security.ErrorCode;

public class CodeNotCorrectException extends ApiException {
    public static ApiException EXCEPTION =
            new CodeNotCorrectException();

    private CodeNotCorrectException(){
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
