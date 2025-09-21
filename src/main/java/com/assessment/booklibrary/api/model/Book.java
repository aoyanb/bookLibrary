package com.assessment.booklibrary.api.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NotNull(message = "ISBN cannot be null")
    @NotEmpty(message = "ISBN cannot be empty")
    private String isbn;
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Author cannot be null")
    @NotEmpty(message = "Author cannot be empty")
    private String author;
}
