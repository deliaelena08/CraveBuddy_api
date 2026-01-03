package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.CakeDto;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class AISyncService {

    private final CakeService cakeService;
    private final RestTemplate restTemplate;
    private final String PYTHON_URL = "http://localhost:8000/sync-cakes";

    public AISyncService(CakeService cakeService) {
        this.cakeService = cakeService;
        this.restTemplate = new RestTemplate();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void syncOnStartup() {
        syncCakesToAI();
    }

    public void syncCakesToAI() {
        try {
            List<CakeDto> cakes = cakeService.findAllCakes();
            restTemplate.postForEntity(PYTHON_URL, cakes, String.class);
            System.out.println("Sincronizare reușită cu serverul AI!");
        } catch (Exception e) {
            System.err.println("Eroare la sincronizarea cu AI: " + e.getMessage());
        }
    }
}