package com.example.security3.controller;

import com.example.security3.constants.ApiConstants;
import com.example.security3.payload.FilterParams;
import com.example.security3.payload.UserCreateDTO;
import com.example.security3.payload.UserDTO;
import com.example.security3.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.USER_API)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get a user by id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<UserDTO> getUserById(@PathVariable Long id){
        log.info("request: get User by id: "+id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Get user pages")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUserPage(FilterParams filterParams){
        log.info("request: get Users page");
        return ResponseEntity.ok(userService.getUserPage(filterParams));
    }

    @Operation(summary = "Create User")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO user) {
        log.info("request: Create User");
        return ResponseEntity.ok(userService.createUser(user));
    }

    @Operation(summary = "Update user by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@RequestBody UserDTO request){
        return userService.updateUser(request);
    }

    @Operation(summary = "Delete user by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
