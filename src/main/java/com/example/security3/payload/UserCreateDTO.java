package com.example.security3.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class UserCreateDTO extends BaseUserDTO{
    private String password;
}
