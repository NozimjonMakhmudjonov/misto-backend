package com.example.security3.payload;

import com.example.security3.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private Role role;
}
