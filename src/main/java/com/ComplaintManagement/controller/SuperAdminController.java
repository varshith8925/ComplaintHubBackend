package com.ComplaintManagement.controller;

import com.ComplaintManagement.model.User;
import com.ComplaintManagement.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {

    private final AuthService authService;

    public SuperAdminController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/admin-requests")
    public ResponseEntity<List<User>> getAdminRequests()
    {
        return new ResponseEntity<>(authService.getAdminRequests(), HttpStatus.OK);
    }

    @PutMapping("/approve-admin/{id}")
    public ResponseEntity<String> approveAdmin(@PathVariable Long id)
    {
        authService.approveAdmin(id);
        return new ResponseEntity<>("Use with the id"+id+"is approved succesfully", HttpStatus.OK);
    }

    @PutMapping("/reject-admin/{id}")
    public ResponseEntity<String> rejectAdmin(@PathVariable Long id)
    {
        authService.rejectAdmin(id);
        return new ResponseEntity<>("User with the id "+id+" is rejected successfully", HttpStatus.OK);
    }
}
