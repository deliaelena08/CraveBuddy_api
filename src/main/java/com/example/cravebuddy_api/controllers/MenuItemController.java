package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.MenuItemDto;
import com.example.cravebuddy_api.exceptions.menuitem.InvalidMenuItemException;
import com.example.cravebuddy_api.exceptions.menuitem.MenuItemAlreadyExistException;
import com.example.cravebuddy_api.exceptions.menuitem.MenuItemNotFoundException;
import com.example.cravebuddy_api.exceptions.user.UserNotFoundException;
import com.example.cravebuddy_api.services.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping()
    public ResponseEntity<?> findAllMenuItems(){
        return ResponseEntity.ok().body(menuItemService.findAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(menuItemService.findMenuItemById(id));
        }
        catch (MenuItemNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveMenuItem(@RequestBody MenuItemDto menuItem){
        try{
            return ResponseEntity.ok(menuItemService.addMenuItem(menuItem));
        } catch ( InvalidMenuItemException | MenuItemAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id){
        try{
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok().build();
        }catch (MenuItemNotFoundException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
