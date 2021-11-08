package com.example.loginpractice.exception;

public class AlreadyUserExistException extends RuntimeException{
    public AlreadyUserExistException(){
        super("AlreadyUserExist");
    }
}
