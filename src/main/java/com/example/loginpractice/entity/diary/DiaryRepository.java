package com.example.loginpractice.entity.diary;

import com.example.loginpractice.payload.response.DiaryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
    List<DiaryResponse> findById(Integer id, Pageable pageable);
}
