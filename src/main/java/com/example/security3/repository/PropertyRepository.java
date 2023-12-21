package com.example.security3.repository;

import com.example.security3.entity.EntProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<EntProperty, Long> {

}
