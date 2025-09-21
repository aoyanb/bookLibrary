package com.assessment.booklibrary.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDetail {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
