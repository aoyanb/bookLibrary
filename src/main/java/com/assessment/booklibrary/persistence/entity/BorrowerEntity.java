package com.assessment.booklibrary.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrower")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
}
