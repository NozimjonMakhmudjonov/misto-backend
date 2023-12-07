package com.example.security3.payload;
import com.example.security3.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class UserCreateDTO {

    @NotBlank
    private String firstname;
    private String lastname;
    @Email(message = "email.not.suitable")
    @NotEmpty
    private String email;
    @NotBlank()
    @Size(min = 6)
    private String password;
    @Size(min = 9)
    private String phoneNumber;
    private String address;
    private Role role;
}
