package com.example.security3.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;
}
