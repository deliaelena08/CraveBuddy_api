package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.Cake;
import org.springframework.stereotype.Component;

@Component
public class CakeValidator {
    public String validate(Cake cake) {
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateName(cake))
                .append(validateIngredients(cake))
                .append(validateSweetness(cake));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateName(Cake cake) {
        if(cake.getName() == null){
            return "Name is required";
        }
        return "";
    }

    private String validateIngredients(Cake cake) {
        if(cake.getIngredients() == null){
            return "Ingredients is required";
        }
        return "";
    }

    private String validateSweetness(Cake cake) {
        if(cake.getSweetness() != null) {
            if (cake.getSweetness() < 0 || cake.getSweetness() > 10) {
                return "Sweetness must be between 0 and 10";
            }
        }
        return "";
    }
}
