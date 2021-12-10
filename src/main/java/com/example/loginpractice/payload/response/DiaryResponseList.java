package com.example.loginpractice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class DiaryResponseList {

    private int user_id;
    private String title;
    private String weather;
    private String contents;
}
