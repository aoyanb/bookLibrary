package com.assessment.booklibrary.api.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDetail {
    private Long id;
    private String isbn;
    private String title;
    private String author;
}
