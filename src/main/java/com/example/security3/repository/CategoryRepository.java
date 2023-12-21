package com.example.security3.repository;

import com.example.security3.entity.EntCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<EntCategory, Long> {
}
