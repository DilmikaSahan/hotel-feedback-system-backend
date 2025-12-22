package com.feedback.feedback_system.controller;

import com.feedback.feedback_system.dto.AdminRequestDto;
import com.feedback.feedback_system.dto.LoginRequestDto;
import com.feedback.feedback_system.jwt.JwtUtil;
import com.feedback.feedback_system.model.Chef;
import com.feedback.feedback_system.model.RoomTable;
import com.feedback.feedback_system.model.Waiter;
import com.feedback.feedback_system.service.AdminService;
import com.feedback.feedback_system.utils.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequestDto  adminRequestDto) {
        return adminService.CreateAdmin(adminRequestDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        boolean valid = adminService.validateAdmin(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (!valid) {
            return ResponseEntity.status(401).body(ResponseCodes.RSP_NOT_AUTHORISED);
        }
        String accessToken = jwtUtil.generateAccessToken(loginRequestDto.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUsername());
        return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
    }
    @PostMapping("/refresh")
    public  ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        if(jwtUtil.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(401).body(ResponseCodes.RSP_TOKEN_EXPIRED);
        }
        String username = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(username);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    //----------------staff Request--------------\\
    //--- waiter ---

    @PostMapping("/addWaiter")
    public ResponseEntity<?> addWaiter(@RequestParam String NewWaiterName) {
        return adminService.addWaiter(NewWaiterName);
    }

    @DeleteMapping("/removeWaiter")
    public ResponseEntity<?> removeWaiter(@RequestParam String NewWaiterName) {
        return adminService.removeWaiter(NewWaiterName);
    }

    //--- chef ---


    @PostMapping("/addChef")
    public ResponseEntity<?> addChef(@RequestParam String NewChefName) {
        return adminService.addChef(NewChefName);
    }

    @DeleteMapping("/removeChef")
    public ResponseEntity<?> removeChef(@RequestParam String NewChefName) {
        return adminService.removeChef(NewChefName);
    }
    //--- Room or table ---


    @PostMapping("/addRoomTable")
    public ResponseEntity<?> addRoomTable(@RequestParam String NewRoomTableName) {
        return adminService.addRoomTale(NewRoomTableName);
    }

    @DeleteMapping("/addRoomTable")
    public ResponseEntity<?> removeRoomTable(@RequestParam String RoomTableName) {
        return adminService.removeRoomTable(RoomTableName);
    }


}
