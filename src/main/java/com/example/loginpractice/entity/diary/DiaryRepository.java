package com.example.loginpractice.entity.diary;

import com.example.loginpractice.payload.response.DiaryResponseList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
}
