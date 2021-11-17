package com.example.loginpractice.controller;

import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    //글쓰기
    @PostMapping("/write")
    public String create(@RequestBody DiaryRequest request){
        return diaryService.create(request);
    }

    //리스트
    @GetMapping("/list")
    public List<DiaryEntity> getAllDiary(){
        return diaryService.getAllDiary();
    }

    //수정
    @PatchMapping("/update/{diaryPk}")
    public String update(@PathVariable Long diaryPk, @RequestBody DiaryRequest request) {
        return diaryService.update(diaryPk, request);
    }

    //삭제
    @DeleteMapping("/delete/{diaryPk}")
    public void delete(@PathVariable Long diaryPk){
        diaryService.delete(diaryPk);
    }
}
