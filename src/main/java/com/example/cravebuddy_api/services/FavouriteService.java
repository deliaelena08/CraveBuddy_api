package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.FavouriteDto;
import com.example.cravebuddy_api.entities.Favourite;
import com.example.cravebuddy_api.exceptions.favourite.FavouriteAlreadyExistException;
import com.example.cravebuddy_api.exceptions.favourite.FavouriteNotFoundException;
import com.example.cravebuddy_api.mappers.FavouriteMapper;
import com.example.cravebuddy_api.repos.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteMapper favouriteMapper;

    public FavouriteDto addFavourite(FavouriteDto favouriteDto) {
        Favourite favourite = favouriteMapper.dtoToEntity(favouriteDto);

        // Prevent duplicates (optional)
        if (favourite.getId() != null && favouriteRepository.existsById(favourite.getId())) {
            throw new FavouriteAlreadyExistException();
        }

        Favourite saved = favouriteRepository.save(favourite);
        return favouriteMapper.entityToDto(saved);
    }

    public void deleteFavourite(Long favouriteId) {
        if (!favouriteRepository.existsById(favouriteId)) {
            throw new FavouriteNotFoundException();
        }
        favouriteRepository.deleteById(favouriteId);
    }

    public FavouriteDto findFavouriteById(Long favouriteId) {
        Favourite favourite = favouriteRepository.findById(favouriteId)
                .orElseThrow(FavouriteNotFoundException::new);
        return favouriteMapper.entityToDto(favourite);
    }

    public List<FavouriteDto> findAllFavourites() {
        return favouriteMapper.entityToDtoList(favouriteRepository.findAll());
    }

    public List<FavouriteDto> findFavouritesByUserId(Long userId) {
        List<Favourite> favourites = favouriteRepository.findByUserId(userId);
        return favouriteMapper.entityToDtoList(favourites);
    }
}
