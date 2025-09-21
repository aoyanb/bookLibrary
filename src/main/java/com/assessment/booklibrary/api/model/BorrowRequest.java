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
public class BorrowRequest {
    @NotNull
    @NotEmpty
    private Long borrowerId;
    @NotNull
    @NotEmpty
    private Long bookId;
}
