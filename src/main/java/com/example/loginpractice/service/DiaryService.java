package com.example.loginpractice.service;

import com.example.loginpractice.dto.DiaryRequest;
import com.example.loginpractice.entity.DiaryEntity;
import com.example.loginpractice.entity.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    //글쓰기
    public String create(DiaryRequest request){
        DiaryEntity diaryEntity = DiaryEntity.builder()
                .title(request.getTitle())
                .weather(request.getWeather())
                .contents(request.getContents())
                .build();
        diaryRepository.save(diaryEntity);
        return "글쓰기 성공";
    }

    //리스트
    public List<DiaryEntity> getAllDiary(){
        return diaryRepository.findAll();
    }
}
