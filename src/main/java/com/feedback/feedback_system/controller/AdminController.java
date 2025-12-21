package com.feedback.feedback_system.controller;

import com.feedback.feedback_system.dto.AdminRequestDto;
import com.feedback.feedback_system.dto.LoginRequestDto;
import com.feedback.feedback_system.jwt.JwtUtil;
import com.feedback.feedback_system.service.AdminService;
import com.feedback.feedback_system.utils.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
