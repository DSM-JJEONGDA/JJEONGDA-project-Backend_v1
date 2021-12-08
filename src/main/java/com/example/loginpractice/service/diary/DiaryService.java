package com.example.loginpractice.service.diary;

import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;

import java.util.List;

public interface DiaryService {
    void create(DiaryRequest request);
    void update(Integer id, DiaryRequest request);
    void delete(Integer id);
    List<DiaryEntity> getAllDiary();
}
