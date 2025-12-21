package com.feedback.feedback_system.model;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Document(collection = "admins")
public class Admin {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean active = true;
    private LocalDateTime createdAt;

}
