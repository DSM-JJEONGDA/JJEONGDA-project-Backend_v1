package com.example.loginpractice.entity.diary;

import com.example.loginpractice.entity.diary.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
}
