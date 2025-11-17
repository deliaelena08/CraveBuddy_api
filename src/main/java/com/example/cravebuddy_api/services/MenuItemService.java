package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.MenuItemDto;
import com.example.cravebuddy_api.entities.MenuItem;
import com.example.cravebuddy_api.exceptions.menuitem.InvalidMenuItemException;
import com.example.cravebuddy_api.exceptions.menuitem.MenuItemAlreadyExistException;
import com.example.cravebuddy_api.exceptions.menuitem.MenuItemNotFoundException;
import com.example.cravebuddy_api.mappers.MenuItemMapper;
import com.example.cravebuddy_api.repos.MenuItemRepository;
import com.example.cravebuddy_api.validators.MenuItemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final MenuItemValidator menuItemValidator;

    public MenuItemDto addMenuItem(MenuItemDto menuItemDto) {
        MenuItem menuItem = menuItemMapper.dtoToEntity(menuItemDto);
        String errs = menuItemValidator.validate(menuItem);
        if(errs.length()>0){
            throw new InvalidMenuItemException();
        }
        if(menuItemRepository.existsById(menuItem.getId())){
            throw new MenuItemAlreadyExistException();
        }
        return menuItemMapper.entityToDto(menuItemRepository.save(menuItem));
    }

    public void deleteMenuItem(Long menuItemId) {
        if(!menuItemRepository.existsById(menuItemId)){
            throw new MenuItemNotFoundException();
        }
        menuItemRepository.deleteById(menuItemId);
    }

    public MenuItemDto findMenuItemById(Long menuItemId) {
        if(!menuItemRepository.existsById(menuItemId)){
            throw new MenuItemNotFoundException();
        }
        return menuItemMapper.entityToDto(menuItemRepository.findById(menuItemId).get());
    }

    public List<MenuItemDto> findAllMenuItems() {
        return menuItemMapper.entityToDto(menuItemRepository.findAll());
    }

    public List<MenuItemDto> findMenuItemsByBakery(Long bakeryId) {
        return menuItemMapper.entityToDto(menuItemRepository.findByBakeryId(bakeryId));
    }
}
