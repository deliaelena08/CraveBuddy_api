package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.BakeryDto;
import com.example.cravebuddy_api.dtos.UserDto;
import com.example.cravebuddy_api.exceptions.bakery.BakeryAlreadyExistException;
import com.example.cravebuddy_api.exceptions.bakery.BakeryNotFoundException;
import com.example.cravebuddy_api.exceptions.bakery.InvalidBakeryException;
import com.example.cravebuddy_api.services.BakeryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bakeries")
public class BakeryController {
    private final BakeryService bakeryService;

    public BakeryController(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }

    @GetMapping()
    public ResponseEntity<List<BakeryDto>> findAllBakeries(){
        return ResponseEntity.ok(bakeryService.findAllBakery());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return  ResponseEntity.ok(bakeryService.findBakeryById(id));
        }catch(BakeryNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveBakery(@Valid @RequestBody BakeryDto bakeryDto){
        try{
            return ResponseEntity.ok(bakeryService.addBakery(bakeryDto));
        }catch(InvalidBakeryException | BakeryAlreadyExistException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBakery(@PathVariable Long id) {
        try {
            bakeryService.deleteBakery(id);
            return ResponseEntity.ok().build();
        }catch(BakeryNotFoundException e){
        return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BakeryDto> updateBakery(@PathVariable Long id, @Valid @RequestBody BakeryDto bakeryDto) {
        return ResponseEntity.ok(bakeryService.updateBakery(id,bakeryDto));
    }
}