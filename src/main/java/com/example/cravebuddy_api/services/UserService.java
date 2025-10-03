package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.LoggedUser;
import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.entities.User;
import com.example.cravebuddy_api.exceptions.user.UserAlreadyExistException;
import com.example.cravebuddy_api.exceptions.user.UserNotFoundException;
import com.example.cravebuddy_api.mappers.UserMapper;
import com.example.cravebuddy_api.repos.UserRepository;
import com.example.cravebuddy_api.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public UserDto login(LoggedUser loginRequest) {
        return userMapper.entityToDto(userRepository.findByUsername(loginRequest.getUsername()));
    }

    public UserDto register(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        String errs = userValidator.validate(user);
        if (errs.length() > 0) {
            throw new IllegalArgumentException(errs);
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistException();
        }
        return userMapper.entityToDto(userRepository.save(user));
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw  new UserNotFoundException("User with id "+id+" not found");
        }
        userRepository.deleteById(id);
    }

    public UserDto findUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return userMapper.entityToDto(userRepository.findById(id).get());
    }

    public List<UserDto> findAllUsers() {
        return userMapper.entityToDto(userRepository.findAll());
    }

    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        if (!existingUser.getUsername().equals(updatedUserDto.getUsername())
                && userRepository.existsByUsername(updatedUserDto.getUsername())) {
            throw new UserAlreadyExistException();
        }

        existingUser.setUsername(updatedUserDto.getUsername());
        existingUser.setLanguage(updatedUserDto.getLanguage());
        existingUser.setLocation(updatedUserDto.getLocation());
        existingUser.setIcon(updatedUserDto.getIcon());

        String errors = userValidator.validateUpdate(existingUser);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors);
        }

        return userMapper.entityToDto(userRepository.save(existingUser));
    }

}
