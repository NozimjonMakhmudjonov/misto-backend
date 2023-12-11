package com.example.security3.repository;

import com.example.security3.entity.EntAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<EntAttachment, Long> {

    Optional<EntAttachment> findByName(String name);
}
