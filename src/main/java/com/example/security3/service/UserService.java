package com.example.security3.service;

import com.example.security3.entity.EntUser;
import com.example.security3.enums.ErrorCode;
import com.example.security3.mapper.UserMapper;
import com.example.security3.payload.FilterParams;
import com.example.security3.payload.UserCreateDTO;
import com.example.security3.payload.UserDTO;
import com.example.security3.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private EntUser getEntUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(ErrorCode.USER_NOT_FOUND))
        );
    }

    public UserDTO getUserById(Long id) {
            return userMapper.toUserDto(getEntUserById(id));
    }


    public Page<UserDTO> getUserPage(FilterParams filterParams) {
        Page<EntUser> userPage = userRepository.findAll(
                PageRequest.of(filterParams.getPage(), filterParams.getSize()));
        return userPage.map(userMapper::toUserDto);
    }

    public HttpEntity<?> createUser(UserCreateDTO user) {
        Optional<EntUser> optionalEntUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (optionalEntUser.isEmpty()) {
            EntUser entUser = userMapper.fromDto(user);
            entUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(entUser);
            return ResponseEntity.status(201).body("User is created");
        }
        return ResponseEntity.status(403).body("User with such email exists");
    }

    public HttpEntity<?> updateUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByEmailAndIdNot(userDTO.getEmail(), userDTO.getId());
        if (exists)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Optional<EntUser> optionalEntUser = userRepository.findById(userDTO.getId());
        if (optionalEntUser.isEmpty()) {
            return ResponseEntity.status(404).body("User is not found!");
        }

        EntUser entUser = optionalEntUser.get();
        userMapper.update(entUser, userDTO);
        userRepository.save(entUser);
        return ResponseEntity.status(404).body("User with such email exists");
    }

    public HttpEntity<?> deleteUser(Long id) {
        Optional<EntUser> optionalEntUser = userRepository.findById(id);
        if (optionalEntUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(202).body("User is deleted");
        }return ResponseEntity.status(404).body("User already has been deleted");

    }
}
