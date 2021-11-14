package com.example.loginpractice.entity.refreshToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash
public class RefreshToken implements Serializable {
    @Id
    private String username;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshTokenExpiration;

    public RefreshToken update(Long refreshTokenExpiration){
        this.refreshTokenExpiration = refreshTokenExpiration;
        return this;
    }
}
