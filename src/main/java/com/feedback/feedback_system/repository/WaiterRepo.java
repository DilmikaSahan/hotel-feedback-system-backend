package com.feedback.feedback_system.repository;

import com.feedback.feedback_system.model.Waiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaiterRepo extends MongoRepository<Waiter,String> {
    Optional<Waiter> findByName(String name);
}
