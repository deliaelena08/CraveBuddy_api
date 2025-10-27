package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.Favourite;
import com.example.cravebuddy_api.entities.Feedback;
import org.springframework.stereotype.Component;

@Component
public class FavouriteValidator {
    public String validate(Favourite favourite){
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateBakery(favourite))
                .append(validateUser(favourite));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateUser(Favourite favourite){
        if(favourite.getUser().getUsername().isEmpty()){
            return "Username is empty";
        }
        return "";
    }

    private String validateBakery(Favourite favourite){
        if(favourite.getBakery() == null){
            return "Bakery is empty";
        }
        return "";
    }
}
