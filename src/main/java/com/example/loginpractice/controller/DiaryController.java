package com.example.loginpractice.controller;

import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;
import com.example.loginpractice.payload.response.DiaryResponseList;
import com.example.loginpractice.service.diary.DiaryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/list/{user_id}")
    public DiaryResponseList getEachDiary(@PathVariable int user_id){
        return diaryService.getEachDiary(user_id);
    }

    @GetMapping("/get/{id}")
    public DiaryResponse getDiary(@PathVariable Integer id){
        return diaryService.getDiary(id);
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
