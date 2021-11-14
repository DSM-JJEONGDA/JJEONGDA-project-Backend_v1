package com.example.loginpractice.service;

import com.example.loginpractice.dto.DiaryRequest;
import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.entity.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //수정
    @Transactional
    public String update(Long diaryPk, DiaryRequest request){

        DiaryEntity diaryEntity = diaryRepository.findById(diaryPk).get();
        diaryEntity.setTitle(request.getTitle());
        diaryEntity.setWeather(request.getWeather());
        diaryEntity.setContents(request.getContents());

        return "수정 성공";
    }

    //삭제
    @Transactional
    public void delete(Long diaryPk){
        diaryRepository.deleteById(diaryPk);
    }
}
