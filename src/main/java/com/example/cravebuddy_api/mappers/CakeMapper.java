package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.CakeDto;
import com.example.cravebuddy_api.entities.Cake;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CakeMapper {
    CakeDto entityToDto(Cake cake);
    List<CakeDto> entityToDto(List<Cake> cakes);
    Cake dtoToEntity(CakeDto cakeDto);
    List<CakeDto> dtoToEntity(List<Cake> cakes);
}
