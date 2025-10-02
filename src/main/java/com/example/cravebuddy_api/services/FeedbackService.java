package com.example.cravebuddy_api.services;

import com.example.cravebuddy_api.dtos.FeedbackDto;
import com.example.cravebuddy_api.entities.Feedback;
import com.example.cravebuddy_api.exceptions.feedback.FeedbackAlreadyExistException;
import com.example.cravebuddy_api.exceptions.feedback.FeedbackNotFoundException;
import com.example.cravebuddy_api.exceptions.feedback.InvalidFeedbackException;
import com.example.cravebuddy_api.mappers.FeedbackMapper;
import com.example.cravebuddy_api.repos.FeedbackRepository;
import com.example.cravebuddy_api.validators.FeedbackValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackValidator feedbackValidator;

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.dtoToEntity(feedbackDto);
        String errs = feedbackValidator.validate(feedback);
        if(errs.length()>0){
            throw new InvalidFeedbackException();
        }
        if(feedbackRepository.existsById(feedback.getId())){
            throw new FeedbackAlreadyExistException();
        }
        return feedbackMapper.entityToDto(feedbackRepository.save(feedback));
    }

    public void deleteFeedback(Long feedbackId) {
        if(!feedbackRepository.existsById(feedbackId)){
            throw new FeedbackNotFoundException();
        }
        feedbackRepository.deleteById(feedbackId);
    }

    public FeedbackDto findFeedbackById(Long feedbackId) {
        if(!feedbackRepository.existsById(feedbackId)){
            throw new FeedbackNotFoundException();
        }
        return feedbackMapper.entityToDto(feedbackRepository.findById(feedbackId).get());
    }

    public List<FeedbackDto> findAllFeedback() {
        return feedbackMapper.entityToDto(feedbackRepository.findAll());
    }
}
