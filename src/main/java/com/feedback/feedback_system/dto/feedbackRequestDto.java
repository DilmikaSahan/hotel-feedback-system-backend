package com.feedback.feedback_system.dto;

import lombok.Data;

@Data
public class feedbackRequestDto {
    private String waiterName;
    private String chefName;
    private String roomTable;

    private String fullName;
    private String phoneNumber;
    private String email;
    private String ItemOrdered;

    private Integer serviceRate;
    private String serviceFeedback;

    private Integer foodRate;
    private String foodFeedback;

    private Integer ambianceRate;
    private String ambianceFeedback;
}
