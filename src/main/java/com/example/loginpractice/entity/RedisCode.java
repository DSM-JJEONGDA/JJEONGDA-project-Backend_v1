package com.example.loginpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;


@RedisHash
@AllArgsConstructor
@Builder
@Getter
public class RedisCode {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private Integer ttl;

    public RedisCode changeCode(String code, Integer ttl){
        this.code = code;
        this.ttl = ttl;
        return this;
    }
}
