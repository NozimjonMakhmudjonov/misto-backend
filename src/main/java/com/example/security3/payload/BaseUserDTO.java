package com.example.security3.payload;

import com.example.security3.enums.Role;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private Role role;
    private String phoneNumber;
}
