package com.feedback.feedback_system.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "feedbacks")
public class FeedBack {
    @Id
    private String id;
    private String waiterName;
    private String chefName;
    private String roomTable;
    //userDetails
    private String fullName;
    private String phoneNumber;
    private String email;
    private String ItemOrdered;
    //feedback details
    private Integer serviceRate;
    private String serviceFeedback;

    private Integer foodRate;
    private String foodFeedback;

    private Integer ambianceRate;
    private String ambianceFeedback;

    private double avgRating;

    private LocalDateTime createdDate;


}
