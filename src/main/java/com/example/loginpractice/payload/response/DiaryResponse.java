package com.example.loginpractice.payload.response;

import com.example.loginpractice.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DiaryResponse {

    private Integer id;
    private String title;
    private String weather;
    private String contents;
}
