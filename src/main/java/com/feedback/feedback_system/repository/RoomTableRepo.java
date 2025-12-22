package com.feedback.feedback_system.repository;

import com.feedback.feedback_system.model.RoomTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTableRepo extends MongoRepository<RoomTable,String> {
    Optional<RoomTable> findByRoomTable(String roomTable);

}
