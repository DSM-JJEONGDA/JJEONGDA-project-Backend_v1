package com.example.loginpractice.controller;

import com.example.loginpractice.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AppController {
    private final AppService appService;

    @GetMapping("/")
    public String hello(){
        return appService.execute();
    }
}
