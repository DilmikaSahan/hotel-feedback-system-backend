package com.feedback.feedback_system.repository;

import com.feedback.feedback_system.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepo extends MongoRepository<Admin,String> {
    Optional<Admin> findByUsername(String username);
}
