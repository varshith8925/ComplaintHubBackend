package com.ComplaintManagement.dto;

import com.ComplaintManagement.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "user name cannot be Blank")
    private String userName;
    @NotBlank(message = "email cannot be Blank")
    @Email(message = "please provide a valid email")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6,message = "password must be atleast 6 charectoers")
    private String password;
    @NotNull(message = "Role must be either user or admin")
    private Role role;

}
