package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemValidator {
    public String validate(MenuItem menuItem){
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateCake(menuItem))
                .append(validateBakery(menuItem))
                .append(validatePrice(menuItem));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateCake(MenuItem menuItem){
        if(menuItem.getCake()==null){
            return "Cake id is required";
        }
        return "";
    }

    private String validateBakery(MenuItem menuItem){
        if(menuItem.getBakery()==null){
            return "Bakery id is required";
        }
        return "";
    }

    private String validatePrice(MenuItem menuItem){
        if(menuItem.getPrice()==null){
            return "Price is required";
        }
        return "";
    }
}
