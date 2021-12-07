package com.example.loginpractice.controller;

import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;
import com.example.loginpractice.payload.response.DiaryResultResponse;
import com.example.loginpractice.service.diary.DiaryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryServiceImpl diaryService;

    //글쓰기
    @PostMapping("/write")
    public void create(@Valid @RequestBody DiaryRequest request){
        diaryService.create(request);
    }

    //리스트
    @GetMapping("/list")
    public DiaryResponse getAllDiary(Integer id){
        return diaryService.getAllDiary(id);
    }

    //수정
    @PatchMapping("/update/{id}")
    public void update(@Valid @PathVariable Integer id,@Valid @RequestBody DiaryRequest request) {
        diaryService.update(id, request);
    }

    //삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@Valid @PathVariable Integer id){
        diaryService.delete(id);
    }
}
