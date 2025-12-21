package com.feedback.feedback_system.repository;

import com.feedback.feedback_system.model.Chef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChefRepo extends MongoRepository<Chef,String> {
    Optional<Chef> findByName(String name);
}
