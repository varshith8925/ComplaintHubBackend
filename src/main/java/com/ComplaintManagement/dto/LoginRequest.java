package com.ComplaintManagement.dto;

import com.ComplaintManagement.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "email cannot be blank")
    @Email(message = "please enter a valid email")
    private String email;
    @NotBlank(message = "password cannot be null")
    private String password;


}
