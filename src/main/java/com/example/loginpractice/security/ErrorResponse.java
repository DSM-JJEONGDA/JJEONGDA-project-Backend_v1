package com.example.loginpractice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String message;

    @Override
    public String toString(){
        return "{\n" +
                "\t\"status\": " + statusCode +
                ",\n\t\"message\": \"" + message + '\"' +
                "\n}";
    }
}
