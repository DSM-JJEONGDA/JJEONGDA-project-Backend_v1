package com.example.loginpractice.entity.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
}
