package com.example.loginpractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppService {

    public String execute(){
        return "HELLO, WORLD!";
    }
}
