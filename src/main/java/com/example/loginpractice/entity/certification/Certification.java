package com.example.loginpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.security.cert.Certificate;


@RedisHash
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Certification implements Serializable {

    @Id @Indexed
    private String email;

    private String code;

    private Certificate certificate;

    private String refreshToken;

    @TimeToLive
    private Integer codeExp;

    public Certification updateCode(String code){
        this.code = code;
        return this;
    }
}
