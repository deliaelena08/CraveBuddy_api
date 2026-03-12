package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.MenuItemDto;
import com.example.cravebuddy_api.entities.MenuItem;
import com.example.cravebuddy_api.exceptions.menuitem.InvalidMenuItemException;
import com.example.cravebuddy_api.exceptions.menuitem.MenuItemNotFoundException;
import com.example.cravebuddy_api.mappers.MenuItemMapper;
import com.example.cravebuddy_api.repos.MenuItemRepository;
import com.example.cravebuddy_api.validators.MenuItemValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private MenuItemMapper menuItemMapper;
    @Mock
    private MenuItemValidator menuItemValidator;

    @InjectMocks
    private MenuItemService menuItemService;

    @Test
    void addMenuItem_ReturnsItem_WhenValid() {
        MenuItemDto dto = new MenuItemDto();
        MenuItem entity = new MenuItem();

        when(menuItemMapper.dtoToEntity(dto)).thenReturn(entity);
        when(menuItemValidator.validate(entity)).thenReturn(""); // Validare OK
        when(menuItemRepository.save(entity)).thenReturn(entity);
        when(menuItemMapper.entityToDto(entity)).thenReturn(dto);

        MenuItemDto result = menuItemService.addMenuItem(dto);

        assertNotNull(result);
        verify(menuItemRepository, times(1)).save(entity);
    }

    @Test
    void updateMenuItem_ThrowsException_WhenItemNotFound() {
        MenuItemDto dto = new MenuItemDto();
        when(menuItemRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.updateMenuItem(99L, dto));
        verify(menuItemRepository, never()).save(any());
    }

    @Test
    void updateMenuItem_UpdatesFields_WhenValid() {
        MenuItem existingItem = new MenuItem();
        existingItem.setPrice(10);

        MenuItemDto updateDto = new MenuItemDto();
        updateDto.setPrice(25);
        updateDto.setQuantity(500);

        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(menuItemValidator.validate(existingItem)).thenReturn("");
        when(menuItemRepository.save(existingItem)).thenReturn(existingItem);
        when(menuItemMapper.entityToDto(existingItem)).thenReturn(updateDto);

        MenuItemDto result = menuItemService.updateMenuItem(1L, updateDto);

        assertEquals(25, existingItem.getPrice());
        assertEquals(500, existingItem.getQuantity());
        verify(menuItemRepository, times(1)).save(existingItem);
    }
}