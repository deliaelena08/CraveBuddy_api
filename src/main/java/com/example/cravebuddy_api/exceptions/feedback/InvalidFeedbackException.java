package com.example.cravebuddy_api.exceptions.feedback;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException() {
        super("Invalid Feedback");
    }
}
