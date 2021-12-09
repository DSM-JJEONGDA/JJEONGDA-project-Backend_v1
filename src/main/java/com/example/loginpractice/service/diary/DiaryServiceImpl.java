package com.example.loginpractice.service.diary;

import com.example.loginpractice.entity.diary.DiaryEntity;
import com.example.loginpractice.entity.diary.DiaryRepository;
import com.example.loginpractice.exception.PostNotFoundException;
import com.example.loginpractice.facade.UserFacade;
import com.example.loginpractice.payload.request.DiaryRequest;
import com.example.loginpractice.payload.response.DiaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    //리스트

    @Override
    @Transactional
    public List<DiaryResponse> getEachDiary(Integer id, Pageable pageable) {
        UserFacade.getUser();
        return diaryRepository.findById(id, pageable);
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