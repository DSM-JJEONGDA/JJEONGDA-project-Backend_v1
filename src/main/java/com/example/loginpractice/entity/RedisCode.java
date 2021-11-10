package com.example.loginpractice.entity;

import jdk.jfr.Timespan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

@NoArgsConstructor
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
