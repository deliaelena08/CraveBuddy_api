package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.FavouriteDto;
import com.example.cravebuddy_api.exceptions.favourite.FavouriteAlreadyExistException;
import com.example.cravebuddy_api.exceptions.favourite.FavouriteNotFoundException;
import com.example.cravebuddy_api.services.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService){
        this.favouriteService = favouriteService;
    }

    @GetMapping()
    public ResponseEntity<List<FavouriteDto>> findAll() {
        return ResponseEntity.ok(favouriteService.findAllFavourites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(favouriteService.findFavouriteById(id));
        } catch (FavouriteNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavouriteDto>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favouriteService.findFavouritesByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<?> addFavourite(@RequestBody FavouriteDto favouriteDto) {
        try {
            return ResponseEntity.ok(favouriteService.addFavourite(favouriteDto));
        } catch (FavouriteAlreadyExistException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long id) {
        try {
            favouriteService.deleteFavourite(id);
            return ResponseEntity.ok().build();
        } catch (FavouriteNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
