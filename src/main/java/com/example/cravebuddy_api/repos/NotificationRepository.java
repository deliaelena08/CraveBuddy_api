package com.example.cravebuddy_api.repos;

import com.example.cravebuddy_api.entities.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
    @Transactional
    void deleteByUserId(Long userId);
}