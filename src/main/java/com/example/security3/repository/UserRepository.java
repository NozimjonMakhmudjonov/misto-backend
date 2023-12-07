package com.example.security3.repository;

import com.example.security3.entity.EntUser;
import com.example.security3.payload.UserAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EntUser, Long> {

    Optional<EntUser> findByEmailIgnoreCase(String email);

    boolean existsByEmailAndIdNot(String email, Long id);


}
