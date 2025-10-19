package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.entities.User;
import com.example.cravebuddy_api.exceptions.user.LoginFailedException;
import com.example.cravebuddy_api.exceptions.user.UserAlreadyExistException;
import com.example.cravebuddy_api.exceptions.user.UserNotFoundException;
import com.example.cravebuddy_api.mappers.UserMapper;
import com.example.cravebuddy_api.repos.UserRepository;
import com.example.cravebuddy_api.security.controller.LoginRequest;
import com.example.cravebuddy_api.utils.UserRole;
import com.example.cravebuddy_api.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public UserDto login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new LoginFailedException("There is no user with the email: " + request.getUsername());
        }
        if (!request.getPassword().equals(user.getPassword())) {
            throw new LoginFailedException("Wrong password");
        }

        User saved = userRepository.save(user);

        Map<String, Object> message = Map.of(
                "type", "UPDATE",
                "payload", userMapper.entityToDto(saved)
        );
        simpMessagingTemplate.convertAndSend("/ws/users", message);
        return userMapper.entityToDto(saved);
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }
        User saved = userRepository.save(user);

        Map<String, Object> message = Map.of(
                "type", "CREATE",
                "payload", userMapper.entityToDto(saved)
        );
        simpMessagingTemplate.convertAndSend("/ws/users", message);

        return userMapper.entityToDto(saved);
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
