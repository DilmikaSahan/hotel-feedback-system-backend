package com.feedback.feedback_system.controller;

import com.feedback.feedback_system.dto.feedbackRequestDto;
import com.feedback.feedback_system.dto.feedbackResponseDto;
import com.feedback.feedback_system.model.Chef;
import com.feedback.feedback_system.model.FeedBack;
import com.feedback.feedback_system.model.RoomTable;
import com.feedback.feedback_system.model.Waiter;
import com.feedback.feedback_system.service.AdminService;
import com.feedback.feedback_system.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/common")
@RequiredArgsConstructor
public class CommonController {
    private final AdminService adminService;
    private final FeedbackService feedbackService;

    @GetMapping("/getAllWaiters")
    public List<Waiter> getAllWaiters() {
        return adminService.getAllWaiter();
    }

    @GetMapping("/getAllChefs")
    public List<Chef> getAllChefs() {
        return adminService.getAllChef();
    }

    @GetMapping("/getAllRoomTables")
    public List<RoomTable> getAllRoomTables() {
        return adminService.getAllRoomTable();
    }

    //---feedbacks
    @PostMapping("/addFeedBack")
    public ResponseEntity<?> addFeedBack(@RequestBody feedbackRequestDto feedBack){
        return feedbackService.addFeedBack(feedBack);
    }


}
