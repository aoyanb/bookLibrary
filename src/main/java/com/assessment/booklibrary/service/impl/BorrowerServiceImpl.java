package com.assessment.booklibrary.service.impl;

import com.assessment.booklibrary.api.mapper.EntityMappers;
import com.assessment.booklibrary.api.model.Borrower;
import com.assessment.booklibrary.persistence.entity.BorrowerEntity;
import com.assessment.booklibrary.persistence.repository.BorrowerRepository;
import com.assessment.booklibrary.service.BorrowerService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    @Nonnull
    private final BorrowerRepository borrowerRepository;
    @Nonnull
    private final EntityMappers mappers;

    @Override
    public Optional<Borrower> findBorrower(Long id) {
        return borrowerRepository.findById(id)
                .map(mappers::fromEntity);
    }

    @Override
    public Optional<Borrower> findBorrower(String email) {
        return borrowerRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public Borrower registerBorrower(Borrower borrower) {
        BorrowerEntity borrowerEntity = new BorrowerEntity();
        borrowerEntity.setEmail(borrower.getEmail());
        borrowerEntity.setName(borrower.getName());

        BorrowerEntity savedBorrower = borrowerRepository.save(borrowerEntity);

        return mappers.fromEntity(savedBorrower);
    }
}
