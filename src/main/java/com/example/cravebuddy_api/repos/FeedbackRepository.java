package com.example.cravebuddy_api.repos;

import com.example.cravebuddy_api.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
