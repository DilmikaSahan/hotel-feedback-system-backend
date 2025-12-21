package com.feedback.feedback_system.service;

import com.feedback.feedback_system.dto.AdminRequestDto;
import com.feedback.feedback_system.model.Admin;
import com.feedback.feedback_system.repository.AdminRepo;
import com.feedback.feedback_system.utils.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> CreateAdmin(AdminRequestDto  adminRequestDto) {
        if(adminRepo.findByUsername(adminRequestDto.getUsername()).isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_DUPLICATED);
        }
        Admin admin = new Admin();
        admin.setUsername(adminRequestDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminRequestDto.getPassword()));
        admin.setRole("ROLE_ADMIN");
        try{
            adminRepo.save(admin);
            return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);

        }catch (Exception e){
            return ResponseEntity.ok(ResponseCodes.RSP_ERROR);
        }
    }
    public boolean validateAdmin(String username, String rawPassword) {
        Admin admin = adminRepo.findByUsername(username).orElseThrow(()->new RuntimeException(("Admin not found!")));
        return passwordEncoder.matches(rawPassword, admin.getPassword());
    }

}
