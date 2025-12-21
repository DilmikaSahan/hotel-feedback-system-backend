package com.feedback.feedback_system.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "waiters")
public class Waiter {
    @Id
    private String id;
    private String name;
}

