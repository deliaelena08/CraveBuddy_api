package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.CakeDto;
import com.example.cravebuddy_api.exceptions.cake.CakeAlreadyExistException;
import com.example.cravebuddy_api.exceptions.cake.CakeNotFoundException;
import com.example.cravebuddy_api.exceptions.cake.InvalidCakeException;
import com.example.cravebuddy_api.services.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cakes")
public class CakeController {
    private CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping
    public ResponseEntity<List<CakeDto>> findAllCakes() {
        return  ResponseEntity.ok(cakeService.findAllCakes());
    }

    @PostMapping
    public ResponseEntity<?> saveCake(@RequestBody CakeDto cakeDto) {
        try{
            return ResponseEntity.ok(cakeService.addCake(cakeDto));
        }catch(InvalidCakeException | CakeAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCake(@PathVariable Long id){
        try {
            cakeService.deleteCake(id);
            return ResponseEntity.ok().build();
        } catch(CakeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(cakeService.findCakeById(id));
        }
        catch (CakeNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
