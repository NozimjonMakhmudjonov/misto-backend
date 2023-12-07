package com.example.security3.controller;

import com.example.security3.payload.LoginDTO;
import com.example.security3.payload.SignUpDTO;
import com.example.security3.payload.UserResponseDTO;
import com.example.security3.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public HttpEntity<?> register(@Valid @RequestBody SignUpDTO request) {
        return authenticationService.signUp(request);

    }

    @PostMapping("/login")
    public HttpEntity<UserResponseDTO> authenticate(@Valid @RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
