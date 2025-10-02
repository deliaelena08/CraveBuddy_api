package com.example.cravebuddy_api.repos;

import com.example.cravebuddy_api.entities.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Long> {
}
