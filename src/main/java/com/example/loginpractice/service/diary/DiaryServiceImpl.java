package com.example.loginpractice.service.diary;

import com.example.loginpractice.facade.UserFacade;
import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.entity.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService{

    private final DiaryRepository diaryRepository;

    //글쓰기
    @Override
    @Transactional
    public void create(DiaryRequest request){
            DiaryEntity diaryEntity = DiaryEntity.builder()
                    .title(request.getTitle())
                    .weather(request.getWeather())
                    .contents(request.getContents())
                    .user(UserFacade.getUser())
                    .build();
            diaryRepository.save(diaryEntity);
    }

    //리스트
    @Override
    @Transactional
    public List<DiaryEntity> getAllDiary(){
        return diaryRepository.findAll();
    }

    //수정
    @Transactional
    @Override
    public void update(Long diaryPk, DiaryRequest request){

        DiaryEntity diaryEntity = diaryRepository.findById(diaryPk).get();
        diaryEntity.setTitle(request.getTitle());
        diaryEntity.setWeather(request.getWeather());
        diaryEntity.setContents(request.getContents());

    }

    //삭제
    @Transactional
    @Override
    public void delete(Long diaryPk){
        diaryRepository.deleteById(diaryPk);
    }
}
