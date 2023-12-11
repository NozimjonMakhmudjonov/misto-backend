package com.example.security3.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO extends BaseUserDTO {
    private String accessToken;
    private String refreshToken;
}
