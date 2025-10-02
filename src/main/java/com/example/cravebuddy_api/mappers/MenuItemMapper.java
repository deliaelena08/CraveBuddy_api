package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.MenuItemDto;
import com.example.cravebuddy_api.entities.MenuItem;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemDto entityToDto(MenuItem menuItem);
    List<MenuItemDto> entityToDto(List<MenuItem> menuItems);
    MenuItem dtoToEntity(MenuItemDto menuItemDto);
    List<MenuItemDto> dtoToEntity(List<MenuItem> menuItems);
}
