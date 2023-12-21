package com.example.security3.repository;

import com.example.security3.entity.EntProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<EntProduct, Long> {
}
