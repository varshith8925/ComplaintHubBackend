package com.ComplaintManagement.dto;

import com.ComplaintManagement.model.ComplaintStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdate {
    @NotNull(message = "the status cannot be null")
    private ComplaintStatus status;
    private String adminRemark;
}
