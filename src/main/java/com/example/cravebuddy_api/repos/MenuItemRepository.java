package com.example.cravebuddy_api.repos;

import com.example.cravebuddy_api.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
