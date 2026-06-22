package com.ComplaintManagement.service;

import com.ComplaintManagement.dto.ComplaintRequest;
import com.ComplaintManagement.dto.ComplaintResponse;
import com.ComplaintManagement.dto.StatusUpdate;
import com.ComplaintManagement.model.Complaint;
import com.ComplaintManagement.model.ComplaintStatus;
import com.ComplaintManagement.model.User;
import com.ComplaintManagement.repo.ComplaintRepository;
import com.ComplaintManagement.repo.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final UserRepo userRepo;
    private final EmailService emailService;

    public ComplaintService(ComplaintRepository complaintRepository,UserRepo userRepo,EmailService emailService) {
        this.complaintRepository = complaintRepository;
        this.userRepo=userRepo;
        this.emailService=emailService;

    }
    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged in user not found"));
    }

    public Complaint submitComplaint(ComplaintRequest complaintRequest)
    {
        Complaint c=new Complaint();
        User user=getLoggedInUser();

        c.setTitle(complaintRequest.getTitle());
        c.setDescription(complaintRequest.getDescription());
        c.setCategory(complaintRequest.getCategory());
        c.setUser(user);
        c.setStatus(ComplaintStatus.PENDING);
        c.setAdminRemark("");
        return complaintRepository.save(c);

    }
    //user-to get submit complaints
    public Complaint getComplaintById(Long id)
    {
        Complaint complaint=complaintRepository.findById(id).orElseThrow(()->
       new RuntimeException("Complaint with the id number:"+id+"not found"));

        return complaint;
    }
    //user to get all his complaints

    public List<Complaint> getMyComplaints() {
        User user = getLoggedInUser();
        return complaintRepository.findByUser(user);
    }

    //user ki idhi kudaa id tho complaint retriev cheyaniki
    public Complaint getMyComplaintById(Long id) {
        User user = getLoggedInUser();
        return complaintRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException(
                        "Complaint not found or does not belong to you"));
    }
    //user own complaint del kosam
    public void deleteMyComplaint(Long id) {
        User user = getLoggedInUser();
        Complaint complaint = complaintRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException(
                        "Complaint not found or does not belong to you"));
        complaintRepository.delete(complaint);
    }

    public List<Complaint> getAllComplaints()
    {
        List<Complaint>complaints=complaintRepository.findAll();
        return  complaints;
    }
    public List<Complaint> getComplaintByStatus(ComplaintStatus status)
    {
        List<Complaint>complaints=complaintRepository.findByStatus(status);
        return complaints;
    }
    public List<Complaint> getComplaintsByCategory(String category)
    {
        List<Complaint>complaints=complaintRepository.findByCategory(category);
        return  complaints;
    }
    public Complaint updateResponse(Long id, StatusUpdate statusUpdate)
    {
        Complaint complaint=complaintRepository.findById(id).orElseThrow(()->
                new RuntimeException("Complaint with the id number: "+id+" not found"));
        complaint.setAdminRemark(statusUpdate.getAdminRemark());
        complaint.setStatus(statusUpdate.getStatus());
        Complaint updatedComplaint=complaintRepository.save(complaint);
        try{
            emailService.sendStatusUpdateMail(
                    updatedComplaint.getUser().getEmail(),
                    updatedComplaint.getUser().getUserName(),
                    updatedComplaint.getTitle(),
                    updatedComplaint.getStatus(),
                    updatedComplaint.getAdminRemark()
            );
        } catch (Exception e) {
            System.err.println("Warning: failed to send the status update mail to " + updatedComplaint.getUser().getEmail());
        }
        return  complaint;

    }
    public void deleteComplaint(Long id)
    {
        complaintRepository.deleteById(id);
    }
    public Map<ComplaintStatus, Long> getDashboardStats() {
        List<Object[]> rawStats = complaintRepository.complaintStatsByStatus();

        Map<ComplaintStatus, Long> statsMap = new HashMap<>();

        for (Object[] row : rawStats) {
            ComplaintStatus status = (ComplaintStatus) row[0];
            Long count = (Long) row[1];

            statsMap.put(status, count);
        }

        return statsMap;
    }


}
