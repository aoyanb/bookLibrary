package com.assessment.booklibrary.api.controller;

import com.assessment.booklibrary.api.error.ErrorReason;
import com.assessment.booklibrary.api.model.Borrower;
import com.assessment.booklibrary.exception.EmailAlreadyExistsException;
import com.assessment.booklibrary.service.BorrowerService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/borrowers")
@RestController
@RequiredArgsConstructor
public class BorrowerController {
    @Nonnull
    private final BorrowerService borrowerService;

    @PostMapping("/register")
    public Borrower register(@RequestBody @Validated Borrower borrower) {
        if (borrowerService.findBorrower(borrower.getEmail()).isPresent()) {
            throw EmailAlreadyExistsException.notAllowed(ErrorReason.EMAIL_ALREADY_EXISTS);
        }
        return borrowerService.registerBorrower(borrower);
    }

    @GetMapping
    public List<Borrower> getAll() {
        return borrowerService.getAllBorrowers();
    }
}
