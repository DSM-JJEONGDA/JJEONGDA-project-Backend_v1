package com.example.loginpractice.entity.diary;

import com.example.loginpractice.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<User> findAllById(Integer id);
}
