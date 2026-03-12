package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.entities.User;
import com.example.cravebuddy_api.exceptions.user.LoginFailedException;
import com.example.cravebuddy_api.exceptions.user.UserAlreadyExistException;
import com.example.cravebuddy_api.mappers.UserMapper;
import com.example.cravebuddy_api.repos.UserRepository;
import com.example.cravebuddy_api.security.controller.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User mockUser;
    private UserDto mockUserDto;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("test@email.com");
        mockUser.setPassword("encodedPassword123");

        mockUserDto = new UserDto();
        mockUserDto.setId(1L);
        mockUserDto.setUsername("test@email.com");
        mockUserDto.setPassword("plainPassword123");
    }

    @Test
    void login_ReturnsUserDto_WhenCredentialsAreValid() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test@email.com");
        loginRequest.setPassword("plainPassword123");

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(mockUser);
        when(passwordEncoder.matches("plainPassword123", "encodedPassword123")).thenReturn(true);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.entityToDto(mockUser)).thenReturn(mockUserDto);

        UserDto result = userService.login(loginRequest);

        assertNotNull(result);
        assertEquals("test@email.com", result.getUsername());

        verify(simpMessagingTemplate, times(1)).convertAndSend(eq("/ws/users"), any(java.util.Map.class));
    }

    @Test
    void login_ThrowsLoginFailedException_WhenUserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("wrong@email.com");

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        Exception exception = assertThrows(LoginFailedException.class, () -> {
            userService.login(loginRequest);
        });

        assertTrue(exception.getMessage().contains("There is no user with the email"));
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void login_ThrowsLoginFailedException_WhenPasswordIsWrong() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test@email.com");
        loginRequest.setPassword("wrongPassword");

        when(userRepository.findByUsername("test@email.com")).thenReturn(mockUser);

        when(passwordEncoder.matches("wrongPassword", "encodedPassword123")).thenReturn(false);

        assertThrows(LoginFailedException.class, () -> {
            userService.login(loginRequest);
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_ThrowsUserAlreadyExistException_WhenUsernameTaken() {
        when(userMapper.dtoToEntity(mockUserDto)).thenReturn(mockUser);

        when(userRepository.existsByUsername("test@email.com")).thenReturn(true);

        assertThrows(UserAlreadyExistException.class, () -> {
            userService.saveUser(mockUserDto);
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_EncodesPasswordAndSaves_WhenValid() {
        when(userMapper.dtoToEntity(mockUserDto)).thenReturn(mockUser);
        when(userRepository.existsByUsername("test@email.com")).thenReturn(false);

        when(passwordEncoder.encode(anyString())).thenReturn("newHashedPassword");

        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.entityToDto(mockUser)).thenReturn(mockUserDto);

        UserDto result = userService.saveUser(mockUserDto);

        assertNotNull(result);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(mockUser);
        verify(simpMessagingTemplate, times(1)).convertAndSend(eq("/ws/users"), any(java.util.Map.class));
    }
}