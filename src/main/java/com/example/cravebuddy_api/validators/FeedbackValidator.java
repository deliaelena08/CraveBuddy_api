package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.Feedback;
import org.springframework.stereotype.Component;

@Component
public class FeedbackValidator {
    public String validate(Feedback feedback){
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateMessage(feedback))
                .append(validateBakery(feedback))
                .append(validateUser(feedback));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateMessage(Feedback feedback){
        if(feedback.getMessage().isEmpty()){
            return "Feedback message is empty";
        }
        return "";
    }

    private String validateUser(Feedback feedback){
        if(feedback.getUser().getUsername().isEmpty()){
            return "Username is empty";
        }
        return "";
    }

    private String validateBakery(Feedback feedback){
        if(feedback.getBakery() == null){
            return "Bakery is empty";
        }
        return "";
    }
}
