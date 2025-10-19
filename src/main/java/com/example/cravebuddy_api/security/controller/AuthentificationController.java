package com.example.cravebuddy_api.security.controller;

import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.exceptions.user.LoginFailedException;
import com.example.cravebuddy_api.security.config.JwtTokenService;
import com.example.cravebuddy_api.services.UserService;
import com.example.cravebuddy_api.utils.UserRole;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthentificationController {

    private  final JwtTokenService jwtTokenService;
    private final UserService userService;

    @PostMapping(value = "/login")
    @SneakyThrows
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        UserDto user;
        try{
            user = userService.login(request);
        } catch (LoginFailedException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }

        String jwt = jwtTokenService.createJwtToken(user.getId(), user.getUsername());
        Cookie cookie = new Cookie("auth-cookie",jwt);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("token", jwt));
    }

    @PostMapping("/signup")
    @SneakyThrows
    public ResponseEntity<?> signup(@RequestBody SignupRequest request, HttpServletResponse response) {
        UserDto user;
        try {
            UserDto userDto = new UserDto();
            userDto.setUsername(request.getUsername());
            userDto.setPassword(request.getPassword());
            userDto.setLanguage(request.getLanguage());
            userDto.setLocation(request.getLocation());
            userDto.setIcon(request.getIcon());
            userDto.setRole(UserRole.USER);
            user = userService.saveUser(userDto);

            String jwt = jwtTokenService.createJwtToken(user.getId(), user.getUsername());
            Cookie cookie = new Cookie("auth-cookie", jwt);
            cookie.setPath("/");
            cookie.setDomain("localhost");
            cookie.setHttpOnly(false);
            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "id", user.getId(),
                            "username", user.getUsername(),
                            "token", jwt
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth-cookie", null);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

}
