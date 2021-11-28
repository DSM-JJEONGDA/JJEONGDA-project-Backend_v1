package com.example.loginpractice.payload.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    @Size(min = 1, max = 20, message = "제목은 1자 이상 20자 이하로 입력해주세요")
    private String title;

    @Size(min = 1, max = 20, message = "날씨는 1자 이상 20자 이하로 입력해주세요")
    private String weather;

    @Size(min = 1, max = 200, message = "내용은 1자 이상 200자 이하로 입력해주세요")
    private String contents;

    @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하로 입력해주세요")
    private String name;
}
