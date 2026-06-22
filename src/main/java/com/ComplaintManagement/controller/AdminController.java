package com.ComplaintManagement.controller;

import com.ComplaintManagement.dto.StatusUpdate;
import com.ComplaintManagement.model.Complaint;
import com.ComplaintManagement.model.ComplaintStatus;
import com.ComplaintManagement.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/complaints")
public class AdminController {
    private final ComplaintService complaintService;

    public AdminController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints()
    {
        return new ResponseEntity<>(complaintService.getAllComplaints(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Complaint>> getComplaintByStatus(@PathVariable ComplaintStatus status)
    {
        return new ResponseEntity<>(complaintService.getComplaintByStatus(status),HttpStatus.OK);
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Complaint>> getComplaintByCategory(@PathVariable String cat)
    {
        return new ResponseEntity<>(complaintService.getComplaintsByCategory(cat),HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Complaint> updateStatusOfComplaint(@PathVariable Long id,@Valid  @RequestBody StatusUpdate statusUpdate)
    {
//        Complaint complaint=complaintService.getComplaintById(id);
//        complaint.setStatus(statusUpdate.getStatus());
//        complaint.setAdminRemark(statusUpdate.getAdminRemark());
         return new ResponseEntity<>(complaintService.updateResponse(id,statusUpdate),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComplaint(@PathVariable Long id)
    {
        complaintService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<ComplaintStatus,Long>> getStats()
    {
        return  new ResponseEntity<>(complaintService.getDashboardStats(),HttpStatus.OK);
    }
}
