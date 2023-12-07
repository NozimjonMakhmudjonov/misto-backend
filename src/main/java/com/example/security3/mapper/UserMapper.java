package com.example.security3.mapper;

import com.example.security3.entity.EntUser;
import com.example.security3.payload.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserAuth toDto(EntUser user);

    UserDTO toUserDto(EntUser user);

    @Mapping(target = "password", ignore = true)
    EntUser fromDto(UserCreateDTO signupDTO);

    UserResponseDTO fromEnt(EntUser user);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget EntUser entUser, UserDTO userDTO);

    EntUser fromDto(SignUpDTO signUpDTO);
}
