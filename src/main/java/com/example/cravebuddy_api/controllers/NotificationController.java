package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.entities.Notification;
import com.example.cravebuddy_api.entities.User;
import com.example.cravebuddy_api.repos.NotificationRepository;
import com.example.cravebuddy_api.repos.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationController(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createNotification(@PathVariable Long userId, @RequestBody Map<String, String> payload) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String message = payload.get("message");
        String type = payload.get("type");
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(type != null ? type : "INFO");

        notificationRepository.save(notification);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAllUserNotifications(@PathVariable Long userId) {
        notificationRepository.deleteByUserId(userId);
        return ResponseEntity.ok().build();
    }
}