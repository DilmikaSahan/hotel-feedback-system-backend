package com.feedback.feedback_system.repository;

import com.feedback.feedback_system.model.FeedBack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends MongoRepository<FeedBack,String> {
}
