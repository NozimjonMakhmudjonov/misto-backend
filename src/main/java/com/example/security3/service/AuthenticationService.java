package com.example.security3.service;

import com.example.security3.entity.EntUser;
import com.example.security3.enums.Role;
import com.example.security3.mapper.UserMapper;
import com.example.security3.payload.LoginDTO;
import com.example.security3.payload.SignUpDTO;
import com.example.security3.payload.UserResponseDTO;
import com.example.security3.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public HttpEntity<?> signUp(SignUpDTO request) {
        Optional<EntUser> optionalEntUser = userRepository.findByEmailIgnoreCase(request.getEmail());
        if (optionalEntUser.isEmpty()) {
            EntUser entUser = userMapper.fromDto(request);
            entUser.setPassword(passwordEncoder.encode(request.getPassword()));
            entUser.setRole(Role.USER);
            userRepository.save(entUser);
            return ResponseEntity.status(201).body("Registration is successful!");
        }
        return ResponseEntity.status(403).body("User with such email exists!");
    }

    public UserResponseDTO login(LoginDTO request) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        EntUser user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return getUserResponseDTO(user);
    }

    private UserResponseDTO getUserResponseDTO(EntUser entUser) {
        String accessToken = jwtService.generateToken(userMapper.toDto(entUser));
        String refreshToken = jwtService.generateRefreshToken(userMapper.toDto(entUser));
        tokenService.saveToken(entUser, accessToken);
        UserResponseDTO userResponseDTO = userMapper.fromEnt(entUser);
        userResponseDTO.setAccessToken(accessToken);
        userResponseDTO.setRefreshToken(refreshToken);
        return userResponseDTO;
    }
}
