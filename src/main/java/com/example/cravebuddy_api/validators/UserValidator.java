package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public String validate(User user) {
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateUsername(user))
                .append(validatePassword(user))
                .append(validateLocation(user));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateUsername(User user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            return "Username is empty";
        }
        if(user.getUsername().length() < 3){
            return "Username should be at least 3 characters";
        }
        return "";
    }

    private String validatePassword(User user) {
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            return "Password is empty";
        }
        if(user.getPassword().length() < 4){
            return "Password should be at least 4 characters";
        }
        return "";
    }

    private String validateLocation(User user) {
        if(user.getLocation() == null || user.getLocation().isEmpty()){
            return "Location is empty";
        }
        return "";
    }
}
