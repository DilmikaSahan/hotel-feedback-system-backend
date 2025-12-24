package com.feedback.feedback_system.service;

import com.feedback.feedback_system.dto.feedbackRequestDto;
import com.feedback.feedback_system.dto.feedbackResponseDto;
import com.feedback.feedback_system.model.FeedBack;
import com.feedback.feedback_system.repository.FeedbackRepo;
import com.feedback.feedback_system.utils.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepo feedbackRepo;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> addFeedBack(feedbackRequestDto feedbackDto){
        FeedBack  feedBack = new FeedBack();
        feedBack.setCreatedDate(LocalDateTime.now());
        feedBack.setAvgRating(calculateAvgRating(feedbackDto.getFoodRate(),feedbackDto.getServiceRate(),feedbackDto.getAmbianceRate()));
        modelMapper.map(feedbackDto,feedBack);
        feedbackRepo.save(feedBack);
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }

    public List<FeedBack> getAllFeedBacks(){
        return feedbackRepo.findAll();
    }
    //calculate avg for one feedback
    private double calculateAvgRating(Integer foodRate,Integer serviceRate,Integer ambianceRate){
        double avg = (foodRate + serviceRate + ambianceRate)/3.0;
        return BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


}
