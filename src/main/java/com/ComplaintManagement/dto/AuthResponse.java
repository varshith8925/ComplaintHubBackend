package com.ComplaintManagement.dto;

import com.ComplaintManagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String userName;
    private String email;
    private Role role;
    private boolean isApproved;

}
