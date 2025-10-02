package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.BakeryDto;
import com.example.cravebuddy_api.entities.Bakery;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BakeryMapper {
    BakeryDto entityToDto(Bakery bakery);
    Bakery dtoToEntity(BakeryDto bakeryDto);
    List<BakeryDto> entityToDto(List<Bakery> bakeries);
    List<Bakery> dtoToEntity(List<BakeryDto> bakeriesDto);
}
