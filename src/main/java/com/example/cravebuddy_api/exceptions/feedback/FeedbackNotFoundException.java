package com.example.cravebuddy_api.exceptions.feedback;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException() {
        super("Feedback not found");
    }
}
