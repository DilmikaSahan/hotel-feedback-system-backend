package com.feedback.feedback_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackStatsDto {
    private int totalReviews;
    private double avgFoodRating;
    private double avgServiceRating;
    private double avgAmbianceRating;

}
