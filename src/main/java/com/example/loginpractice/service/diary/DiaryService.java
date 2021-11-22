package com.example.loginpractice.service.diary;

import com.example.loginpractice.payload.request.DiaryRequest;

public interface DiaryService {
    void create(DiaryRequest request);
    void update(Long id, DiaryRequest request);
    void delete(Long id);
}
