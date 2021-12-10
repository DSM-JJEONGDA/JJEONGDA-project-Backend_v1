package com.example.loginpractice.service.diary;

import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.entity.diary.DiaryRepository;
import com.example.loginpractice.exception.PostNotFoundException;
import com.example.loginpractice.facade.UserFacade;
import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;
import com.example.loginpractice.payload.response.DiaryResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    //글쓰기
    @Override
    @Transactional
    public void create(DiaryRequest request) {
        DiaryEntity diaryEntity = diaryRepository.save(DiaryEntity.builder()
                .title(request.getTitle())
                .weather(request.getWeather())
                .contents(request.getContents())
                .user(UserFacade.getUser())
                .build());
    }

    @Override
    @Transactional
    public DiaryResponse getDiary(Integer id){
        return diaryRepository.findById(id)
                .map(diaryEntity -> {
                    DiaryResponse response = DiaryResponse.builder()
                            .id(diaryEntity.getId())
                            .title(diaryEntity.getTitle())
                            .weather(diaryEntity.getWeather())
                            .contents(diaryEntity.getContents())
                            .build();
                    return response;
                })
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    //리스트
    @Transactional
    @Override
    public DiaryResponseList getEachDiary(int user_id) {
        return diaryRepository.findById(user_id)
                .map(diaryEntity -> {
                    DiaryResponseList responseList = DiaryResponseList.builder()
                            .user_id(diaryEntity.getUser().getId())
                            .contents(diaryEntity.getContents())
                            .title(diaryEntity.getTitle())
                            .weather(diaryEntity.getWeather())
                            .build();
                    return responseList;
                })
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    //수정
    @Transactional
    @Override
    public void update(Integer id, DiaryRequest request) {
        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .map(newDiaryEntity -> newDiaryEntity.updateDiary(
                        request.getContents(),
                        request.getTitle(),
                        request.getWeather()
                ))
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    //삭제
    @Transactional
    @Override
    public void delete(Integer id) {
        diaryRepository.deleteById(id);
    }


    private boolean checkMine(Integer id) {
        Integer userId = UserFacade.getUserId();

        return diaryRepository.findById(id)
                .filter(u -> userId != null)
                .map(diary -> diary.getUser().getId().equals(userId))
                .isPresent();
    }
}