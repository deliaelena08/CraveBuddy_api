package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.BakeryDto;
import com.example.cravebuddy_api.entities.Bakery;
import com.example.cravebuddy_api.exceptions.bakery.BakeryAlreadyExistException;
import com.example.cravebuddy_api.exceptions.bakery.BakeryNotFoundException;
import com.example.cravebuddy_api.exceptions.bakery.InvalidBakeryException;
import com.example.cravebuddy_api.mappers.BakeryMapper;
import com.example.cravebuddy_api.repos.BakeryRepository;
import com.example.cravebuddy_api.validators.BakeryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BakeryService {
    private final BakeryRepository bakeryRepository;
    private final BakeryMapper bakeryMapper;
    private final BakeryValidator bakeryValidator;

    public BakeryDto addBakery(BakeryDto bakeryDto) {
        Bakery bakery = bakeryMapper.dtoToEntity(bakeryDto);
        String errs = bakeryValidator.validate(bakery);
        if(errs.length()>0){
            throw new InvalidBakeryException();
        }
        if(bakeryRepository.existsById(bakery.getId())){
            throw new BakeryAlreadyExistException();
        }
        return bakeryMapper.entityToDto(bakeryRepository.save(bakery));
    }

    public void deleteBakery(Long bakeryId) {
        if(!bakeryRepository.existsById(bakeryId)){
            throw new BakeryNotFoundException();
        }
        bakeryRepository.deleteById(bakeryId);
    }

    public BakeryDto findBakeryById(Long bakeryId) {
        if(!bakeryRepository.existsById(bakeryId)){
            throw new BakeryNotFoundException();
        }
        return bakeryMapper.entityToDto(bakeryRepository.findById(bakeryId).get());
    }

    public List<BakeryDto> findAllBakery() {
        return bakeryMapper.entityToDto(bakeryRepository.findAll());
    }
}
