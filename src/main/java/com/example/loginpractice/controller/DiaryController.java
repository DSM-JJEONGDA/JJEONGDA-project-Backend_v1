package com.example.loginpractice.controller;

import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.service.diary.DiaryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<DiaryEntity> getAllDiary(Integer id){
        return diaryService.getAllDiary(id);
    }

    //수정
    @PatchMapping("/update/{diaryPk}")
    public void update(@Valid @PathVariable Long diaryPk,@Valid @RequestBody DiaryRequest request) {
        diaryService.update(diaryPk, request);
    }

    //삭제
    @DeleteMapping("/delete/{diaryPk}")
    public void delete(@Valid @PathVariable Long diaryPk){
        diaryService.delete(diaryPk);
    }
}
