package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.CakeDto;
import com.example.cravebuddy_api.entities.Cake;
import com.example.cravebuddy_api.exceptions.bakery.BakeryAlreadyExistException;
import com.example.cravebuddy_api.exceptions.bakery.BakeryNotFoundException;
import com.example.cravebuddy_api.exceptions.bakery.InvalidBakeryException;
import com.example.cravebuddy_api.mappers.CakeMapper;
import com.example.cravebuddy_api.repos.CakeRepository;
import com.example.cravebuddy_api.validators.CakeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CakeService {
    private final CakeRepository cakeRepository;
    private final CakeMapper cakeMapper;
    private final CakeValidator cakeValidator;

    public CakeDto addCake(CakeDto cakeDto) {
        Cake cake = cakeMapper.dtoToEntity(cakeDto);

        String error = cakeValidator.validate(cake);
        if(error.length() > 0) {
            throw new InvalidBakeryException();
        }

        return cakeMapper.entityToDto(cakeRepository.save(cake));
    }

    public void deleteCake(Long cakeId) {
        if(!cakeRepository.existsById(cakeId)) {
            throw new BakeryNotFoundException();
        }
        cakeRepository.deleteById(cakeId);
    }

    public CakeDto findCakeById(Long cakeId) {
        return cakeRepository.findById(cakeId)
                .map(cakeMapper::entityToDto)
                .orElseThrow(BakeryNotFoundException::new);
    }

    public List<CakeDto> findAllCakes() {
        return cakeMapper.entityToDto(cakeRepository.findAll());
    }
}