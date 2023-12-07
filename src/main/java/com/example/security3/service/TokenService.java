package com.example.security3.service;


import com.example.security3.entity.EntToken;
import com.example.security3.entity.EntUser;
import com.example.security3.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.security3.enums.TokenType.BEARER;


@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(EntUser user, String token) {
        EntToken entToken = new EntToken();
        entToken.setUserId(user.getId());
        entToken.setToken(token);
        entToken.setTokenType(BEARER);
        entToken.setExpired(false);
        entToken.setRevoked(false);
//        entToken.setCreatedBy(-1L);
//        entToken.setLastModifiedBy(-1L);
        tokenRepository.save(entToken);
    }
}
