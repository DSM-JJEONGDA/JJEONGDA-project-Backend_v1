package com.example.loginpractice.entity.certification;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CertificationRepository extends CrudRepository<Certification, String> {
    Optional<Certification> findByEmail(String email);
}
