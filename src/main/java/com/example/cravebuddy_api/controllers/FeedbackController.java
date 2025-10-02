package com.example.cravebuddy_api.controllers;

import com.example.cravebuddy_api.dtos.FeedbackDto;
import com.example.cravebuddy_api.exceptions.feedback.FeedbackAlreadyExistException;
import com.example.cravebuddy_api.exceptions.feedback.FeedbackNotFoundException;
import com.example.cravebuddy_api.exceptions.feedback.InvalidFeedbackException;
import com.example.cravebuddy_api.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public ResponseEntity<List<FeedbackDto>> findAll(){
        return ResponseEntity.ok(feedbackService.findAllFeedback());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(feedbackService.findFeedbackById(id));
        }
        catch (FeedbackNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveFeedback(@RequestBody FeedbackDto feedbackDto){
        try {
            return ResponseEntity.ok(feedbackService.addFeedback(feedbackDto));
        }catch(InvalidFeedbackException | FeedbackAlreadyExistException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id){
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok().build();
        }catch(FeedbackNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
