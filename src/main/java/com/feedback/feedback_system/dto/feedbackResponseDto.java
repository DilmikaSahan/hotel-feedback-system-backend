package com.feedback.feedback_system.dto;

import java.time.LocalDateTime;

public class feedbackResponseDto {
    private String id;
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

    private LocalDateTime createdDate;

}
