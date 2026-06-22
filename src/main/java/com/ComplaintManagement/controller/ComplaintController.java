package com.ComplaintManagement.controller;

import com.ComplaintManagement.dto.ComplaintRequest;
import com.ComplaintManagement.model.Complaint;
import com.ComplaintManagement.repo.UserRepo;
import com.ComplaintManagement.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;


    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;

    }

    @PostMapping
    public ResponseEntity<Complaint> submitComplaint(@Valid @RequestBody  ComplaintRequest complaintRequest)
    {
        Complaint savedComplaint=complaintService.submitComplaint(complaintRequest);
        return  new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id)
    {
            Complaint complaint=complaintService.getComplaintById(id);
            return new ResponseEntity<>(complaint,HttpStatus.OK);
    }
    @GetMapping("/my")
    public ResponseEntity<List<Complaint>> trackComplaintByEmail()
    {
        List<Complaint>complaints=complaintService.getMyComplaints();
        return new ResponseEntity<>(complaints,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMyComplaint(@PathVariable Long id) {
        complaintService.deleteMyComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully");
    }

}
