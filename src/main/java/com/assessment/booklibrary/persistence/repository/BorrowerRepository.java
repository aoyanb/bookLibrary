package com.assessment.booklibrary.persistence.repository;

import com.assessment.booklibrary.api.model.Borrower;
import com.assessment.booklibrary.persistence.entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Long> {
    Optional<Borrower> findByEmail(String email);
}
