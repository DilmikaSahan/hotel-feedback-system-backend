package com.feedback.feedback_system.service;

import com.feedback.feedback_system.dto.AdminRequestDto;
import com.feedback.feedback_system.model.Admin;
import com.feedback.feedback_system.model.Chef;
import com.feedback.feedback_system.model.RoomTable;
import com.feedback.feedback_system.model.Waiter;
import com.feedback.feedback_system.repository.AdminRepo;
import com.feedback.feedback_system.repository.ChefRepo;
import com.feedback.feedback_system.repository.RoomTableRepo;
import com.feedback.feedback_system.repository.WaiterRepo;
import com.feedback.feedback_system.utils.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepo adminRepo;
    private  final WaiterRepo waiterRepo;
    private final ChefRepo chefRepo;
    private final RoomTableRepo roomTableRepo;
    private final PasswordEncoder passwordEncoder;
//--------------admin management-------------------------------------
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

    //--------------------------staff management-----------------------
    //waiter management
    public ResponseEntity<?> addWaiter(String waiterName){
        Optional<Waiter> waiter = waiterRepo.findByName(waiterName);
        if(waiter.isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_DUPLICATED);
        }
        Waiter waiter1 = new Waiter();
        waiter1.setName(waiterName);
        waiterRepo.save(waiter1);
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public ResponseEntity<?> removeWaiter(String waiterName){
        Optional<Waiter> waiter = waiterRepo.findByName(waiterName);
        if(!waiter.isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_NO_DATA_FOUND);
        }
        waiterRepo.delete(waiter.get());
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public List<Waiter> getAllWaiter() {
        return waiterRepo.findAll();
    }
    //chef management
    public ResponseEntity<?> addChef(String chefName){
        Optional<Chef> chef = chefRepo.findByName(chefName);
        if (chef.isPresent()) {
            return ResponseEntity.ok(ResponseCodes.RSP_DUPLICATED);
        }
        Chef chef1 = new Chef();
        chef1.setName(chefName);
        chefRepo.save(chef1);
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public  ResponseEntity<?> removeChef(String chefName){
        Optional<Chef> chef = chefRepo.findByName(chefName);
        if(!chef.isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_NO_DATA_FOUND);
        }
        chefRepo.delete(chef.get());
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public List<Chef> getAllChef() {
        return chefRepo.findAll();
    }
    //room and table management
    public ResponseEntity<?> addRoomTale(String roomTableName){
        Optional<RoomTable> roomTable =  roomTableRepo.findByName(roomTableName);
        if(roomTable.isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_DUPLICATED);
        }
        RoomTable newRoomTable = new RoomTable();
        newRoomTable.setRoomTable(roomTableName);
        roomTableRepo.save(newRoomTable);
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public ResponseEntity<?> removeRoomTable(String roomTableName){
        Optional<RoomTable> roomTable = roomTableRepo.findByName(roomTableName);
        if(!roomTable.isPresent()){
            return ResponseEntity.ok(ResponseCodes.RSP_NO_DATA_FOUND);
        }
        roomTableRepo.delete(roomTable.get());
        return ResponseEntity.ok(ResponseCodes.RSP_SUCCESS);
    }
    public List<RoomTable> getAllRoomTable() {
        return roomTableRepo.findAll();
    }
}
