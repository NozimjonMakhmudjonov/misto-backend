package com.example.security3.repository;

import com.example.security3.entity.EntToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<EntToken, Long> {

    @Query(value = """
            select t from EntToken t inner join EntUser u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<EntToken> findAllValidTokenByUser(Integer id);

    Optional<EntToken> findByToken(String token);
}
