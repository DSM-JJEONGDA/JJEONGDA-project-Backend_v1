package com.example.loginpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    @Size(min = 1, max = 20, message = "제목은 1자 이상 20자 이하로 입력해주세요")
    private String title;

    @Size(min = 1, max = 12, message = "날씨는 1자 이상 12자 이하로 입력해주세요")
    private String weather;

    @Size(min = 1, max = 500, message = "내용은 1자 이상 500자 이하로 입력해주세요")
    private String contents;
}