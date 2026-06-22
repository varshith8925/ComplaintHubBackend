package com.ComplaintManagement.service;

import com.ComplaintManagement.model.ComplaintStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendStatusUpdateMail(String toMail, String userName,
                                     String complaintTitle, ComplaintStatus complaintStatus,
                                     String adminRemark
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("varshith730@gmail.com");
        message.setTo(toMail);
        message.setSubject("Update on Your Complaint: " + complaintTitle);
        String emailBody = String.format(
                "Dear %s,\n\n" +
                        "The status of your complaint titled '%s' has been updated.\n\n" +
                        "Current Status: %s\n" +
                        "Admin Remarks: %s\n\n" +
                        "Thank you for your patience.\n" +
                        "Support Management Team",
                userName, complaintTitle, complaintStatus, adminRemark
        );
        message.setText(emailBody);
        mailSender.send(message);
    }
}
