package com.example.cravebuddy_api.mappers;

import com.example.cravebuddy_api.dtos.FeedbackDto;
import com.example.cravebuddy_api.entities.Feedback;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackDto entityToDto(Feedback feedback);
    Feedback dtoToEntity(FeedbackDto feedbackDto);
    List<FeedbackDto> entityToDto(List<Feedback> feedbacks);
    List<Feedback> dtoToEntity(List<FeedbackDto> feedbackDtos);
}
