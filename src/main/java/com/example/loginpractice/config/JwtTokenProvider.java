package com.example.loginpractice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey = "webfirewood";

    //토큰 유효기간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk); //jwt playload에 저장되는 정보 단위
    }
}
