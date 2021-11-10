package com.example.loginpractice.service;

import com.example.loginpractice.entity.RedisCode;
import com.example.loginpractice.entity.RedisRepository;
import com.example.loginpractice.exception.CodeNotCorrectException;
import com.example.loginpractice.exception.EmailNotCorrectException;
import com.example.loginpractice.payload.CodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyCodeService {
    private static final Integer REDIS_TTL = 60 * 60 * 12;

    private final RedisRepository redisRepository;

    public String execute(CodeRequest request){
        RedisCode redisCode = redisRepository.findById(request.getEmail())
                .orElseThrow(()-> EmailNotCorrectException.EXCEPTION);
        if(!redisCode.getCode().equals(request.getCode())){
            throw CodeNotCorrectException.EXCEPTION;
        }

        redisRepository.save(
                redisCode.changeCode("verified", REDIS_TTL)
        );
        return "Verified";
    }
}
