package com.example.cravebuddy_api.repos;

import com.example.cravebuddy_api.entities.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUserId(Long userId);
}
