package com.ComplaintManagement.dto;

import com.ComplaintManagement.model.ComplaintStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {
    private Long id;

    private String title;


    private String description;

    private String category;

    private String userName;

    private String userEmail;

    private ComplaintStatus status=ComplaintStatus.PENDING;

    private String adminRemark;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
