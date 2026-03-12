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
import org.junit.jupiter.api.BeforeEach;
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
class BakeryServiceTest {

    @Mock
    private BakeryRepository bakeryRepository;
    @Mock
    private BakeryMapper bakeryMapper;
    @Mock
    private BakeryValidator bakeryValidator;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BakeryService bakeryService;

    private Bakery bakery;
    private BakeryDto bakeryDto;

    @BeforeEach
    void setUp() {
        bakery = new Bakery();
        bakery.setId(1L);
        bakery.setName("Dulcinella");

        bakeryDto = new BakeryDto();
        bakeryDto.setId(1L);
        bakeryDto.setName("Dulcinella");
        bakeryDto.setOwnerId(10L);
    }

    @Test
    void addBakery_ReturnsSavedBakery_WhenValidAndOwnerExists() {
        User mockOwner = new User();
        mockOwner.setId(5L);

        when(bakeryMapper.dtoToEntity(bakeryDto)).thenReturn(bakery);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockOwner));

        when(bakeryValidator.validate(bakery)).thenReturn("");
        when(bakeryRepository.save(bakery)).thenReturn(bakery);
        when(bakeryMapper.entityToDto(bakery)).thenReturn(bakeryDto);

        BakeryDto result = bakeryService.addBakery(bakeryDto);

        assertNotNull(result);
        assertEquals(5L, bakery.getOwner().getId());
        verify(bakeryRepository, times(1)).save(bakery);
    }

    @Test
    void addBakery_ThrowsException_WhenValidationFails() {
        when(bakeryMapper.dtoToEntity(bakeryDto)).thenReturn(bakery);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));

        when(bakeryValidator.validate(bakery)).thenReturn("Nume invalid");

        assertThrows(InvalidBakeryException.class, () -> bakeryService.addBakery(bakeryDto));
        verify(bakeryRepository, never()).save(any());
    }

    @Test
    void updateBakery_UpdatesAndReturnsBakery_WhenValid() {
        BakeryDto updateDto = new BakeryDto();
        updateDto.setName("Nume Nou");

        when(bakeryRepository.findById(anyLong())).thenReturn(Optional.of(bakery));
        when(bakeryValidator.validate(bakery)).thenReturn("");
        when(bakeryRepository.save(bakery)).thenReturn(bakery);
        when(bakeryMapper.entityToDto(bakery)).thenReturn(updateDto);

        BakeryDto result = bakeryService.updateBakery(1L, updateDto);

        assertEquals("Nume Nou", result.getName());
        verify(bakeryRepository, times(1)).save(bakery);
    }

    @Test
    void deleteBakery_ThrowsException_WhenNotFound() {
        when(bakeryRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(BakeryNotFoundException.class, () -> bakeryService.deleteBakery(99L));
        verify(bakeryRepository, never()).deleteById(anyLong());
    }
}