package com.example.cravebuddy_api.validators;

import com.example.cravebuddy_api.entities.Bakery;
import org.springframework.stereotype.Component;

@Component
public class BakeryValidator {
    public String validate(Bakery bakery){
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateName(bakery))
                .append(validateCity(bakery))
                .append(validateLongitude(bakery))
                .append(validateLatitude(bakery))
                .append(validateAddress(bakery));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    private String validateName(Bakery bakery){
        if(bakery.getName().isEmpty()){
            return "Name is required";
        }
        return "";
    }

    private String validateCity(Bakery bakery){
        if(bakery.getCity().isEmpty()){
            return "City is required";
        }
        return "";
    }

    private String validateLongitude(Bakery bakery){
        if(bakery.getLongitude() == null){
            return "Longitude is required";
        }
        return "";
    }

    private String validateLatitude(Bakery bakery){
        if(bakery.getLatitude() == null){
            return "Latitude is required";
        }
        return "";
    }

    private String validateAddress(Bakery bakery){
        if(bakery.getAddress() == null){
            return "Address is required";
        }
        return "";
    }
}
