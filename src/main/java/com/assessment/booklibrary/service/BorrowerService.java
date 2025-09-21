package com.assessment.booklibrary.service;

import com.assessment.booklibrary.api.model.Borrower;

import java.util.List;
import java.util.Optional;

public interface BorrowerService {
    Optional<Borrower> findBorrower(Long id);
    Optional<Borrower> findBorrower(String email);
    Borrower registerBorrower(Borrower borrower);
    List<Borrower> getAllBorrowers();
}
