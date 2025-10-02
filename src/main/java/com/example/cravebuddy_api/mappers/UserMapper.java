package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.entities.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);
    List<UserDto> entityToDto(List<User> users);
    List<UserDto> dtoToEntity(List<UserDto> userDtos);
}
