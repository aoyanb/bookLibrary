package com.assessment.booklibrary.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrow_record")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long borrowerId;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String isbn;
}
