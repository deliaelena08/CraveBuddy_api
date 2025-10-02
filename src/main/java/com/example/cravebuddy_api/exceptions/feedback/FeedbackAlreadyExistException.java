package com.example.cravebuddy_api.exceptions.feedback;

public class FeedbackAlreadyExistException extends RuntimeException{
    public FeedbackAlreadyExistException() {
        super("Feedback already exists");
    }
}
