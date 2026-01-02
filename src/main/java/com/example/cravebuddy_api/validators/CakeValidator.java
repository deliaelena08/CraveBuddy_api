package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.Cake;
import org.springframework.stereotype.Component;

@Component
public class CakeValidator {
    public String validate(Cake cake) {
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateNameRo(cake))
                .append(validateIngredientsRo(cake))
                .append(validateSweetness(cake));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateNameRo(Cake cake) {
        if(cake.getNameRo() == null || cake.getNameRo().trim().isEmpty()){
            return "Romanian Name is required; ";
        }
        return "";
    }

    private String validateIngredientsRo(Cake cake) {
        if(cake.getIngredientsRo() == null || cake.getIngredientsRo().trim().isEmpty()){
            return "Romanian Ingredients are required; ";
        }
        return "";
    }

    private String validateSweetness(Cake cake) {
        if(cake.getSweetness() != null) {
            if (cake.getSweetness() < 0 || cake.getSweetness() > 10) {
                return "Sweetness must be between 0 and 10; ";
            }
        }
        return "";
    }
}