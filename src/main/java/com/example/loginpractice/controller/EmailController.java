package com.example.loginpractice.controller;

import com.example.loginpractice.payload.CodeRequest;
import com.example.loginpractice.payload.EmailRequest;
import com.example.loginpractice.service.SendEmailService;
import com.example.loginpractice.service.VerifyCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailService sendEmailService;
    private final VerifyCodeService verifyCodeService;

    @PostMapping("/email")
    public void SendEmail(@RequestBody @Valid EmailRequest request){
        sendEmailService.execute(request);
    }

    @PutMapping("verified")
    public String VerifyCode(@RequestBody @Valid CodeRequest request){
        return verifyCodeService.execute(request);
    }
}
