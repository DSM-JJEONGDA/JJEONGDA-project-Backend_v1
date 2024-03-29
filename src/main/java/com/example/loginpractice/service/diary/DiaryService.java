package com.example.loginpractice.service.diary;

import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;
import com.example.loginpractice.payload.response.DiaryResponseList;

import java.util.List;

public interface DiaryService {
    void create(DiaryRequest request);
    void update(Integer id, DiaryRequest request);
    void delete(Integer id);
    List<DiaryResponseList> getEachDiary(int userId);
    DiaryResponse getDiary(Integer id);
}
