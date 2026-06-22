package com.ComplaintManagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequest {

    @Size(max = 100,message = "the title should not exceed more than 100 charecters")
    private String title;

    @Size(max=5000)
    @NotBlank(message = "the description cannot be null and not more than 5000 charecters")
    private String description;

    private String category;

}
