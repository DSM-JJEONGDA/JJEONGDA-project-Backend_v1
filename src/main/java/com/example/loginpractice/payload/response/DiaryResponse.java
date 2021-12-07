package com.example.loginpractice.payload.response;

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
    private boolean isMine;
}
