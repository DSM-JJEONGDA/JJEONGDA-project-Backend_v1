package com.example.loginpractice.service.diary;

import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;

public interface DiaryService {
    void create(DiaryRequest request);
    void update(Integer id, DiaryRequest request);
    void delete(Integer id);
    DiaryResponse getAllDiary(Integer id);
}
