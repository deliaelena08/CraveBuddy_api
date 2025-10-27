package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.FavouriteDto;
import com.example.cravebuddy_api.entities.Favourite;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {
    FavouriteDto entityToDto(Favourite favourite);
    Favourite dtoToEntity(FavouriteDto favouriteDto);
    List<FavouriteDto> entityToDtoList(List<Favourite> list);
    List<FavouriteDto> dtoToEntityList(List<FavouriteDto> list);
}
