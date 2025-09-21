package com.assessment.booklibrary.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {
    @NotNull(message = "Borrower's name cannot be null")
    @NotEmpty(message = "Borrower's name cannot be empty")
    private String name;
    @NotNull(message = "Borrower's email cannot be null")
    @NotEmpty(message = "Borrower's email cannot be empty")
    @Email(message = "Please provide a valid email")
    private String email;
}
