package com.example.loginpractice.entity;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RedisCode, String> {
}
