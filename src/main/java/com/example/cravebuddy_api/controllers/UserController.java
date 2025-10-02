package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.exceptions.user.InvalidUserException;
import com.example.cravebuddy_api.exceptions.user.UserAlreadyExistException;
import com.example.cravebuddy_api.exceptions.user.UserNotFoundException;
import com.example.cravebuddy_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.findUserById(id));
        }
        catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
        try {
            return ResponseEntity.ok(userService.register(user));
        }catch (InvalidUserException | UserAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch(UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
