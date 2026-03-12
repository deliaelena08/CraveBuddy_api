package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.BakeryDto;
import com.example.cravebuddy_api.entities.Bakery;
import com.example.cravebuddy_api.entities.User;
import com.example.cravebuddy_api.exceptions.bakery.BakeryNotFoundException;
import com.example.cravebuddy_api.exceptions.bakery.InvalidBakeryException;
import com.example.cravebuddy_api.mappers.BakeryMapper;
import com.example.cravebuddy_api.repos.BakeryRepository;
import com.example.cravebuddy_api.repos.UserRepository;
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
    private final UserRepository userRepository;

    public BakeryDto addBakery(BakeryDto bakeryDto) {
        Bakery bakery = bakeryMapper.dtoToEntity(bakeryDto);

        if (bakeryDto.getOwnerId() != null) {
            User owner = userRepository.findById(bakeryDto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            bakery.setOwner(owner);
        }

        String errs = bakeryValidator.validate(bakery);
        if(errs.length() > 0){
            throw new InvalidBakeryException();
        }
        Bakery savedBakery = bakeryRepository.save(bakery);
        return bakeryMapper.entityToDto(savedBakery);
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

    public BakeryDto updateBakery(Long id,BakeryDto newBakeryDto) {
        Bakery existingBakery = bakeryRepository.findById(id) .orElseThrow(() -> new BakeryNotFoundException());
        existingBakery.setName(newBakeryDto.getName());
        existingBakery.setAddress(newBakeryDto.getAddress());
        existingBakery.setLatitude(newBakeryDto.getLatitude());
        existingBakery.setLongitude(newBakeryDto.getLongitude());

        String errs = bakeryValidator.validate(existingBakery);
        if(errs.length() > 0){
            throw new InvalidBakeryException();
        }
        return bakeryMapper.entityToDto(bakeryRepository.save(existingBakery));

    }
}
