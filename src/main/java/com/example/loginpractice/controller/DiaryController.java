package com.example.loginpractice.controller;

import com.example.loginpractice.dto.DiaryRequest;
import com.example.loginpractice.entity.DiaryEntity;
import com.example.loginpractice.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    //글쓰기
    @PostMapping
    public String create(DiaryRequest request){
        return diaryService.create(request);
    }

    @GetMapping("/list")
    public List<DiaryEntity> getAllDiary(){
        return diaryService.
    }
}
