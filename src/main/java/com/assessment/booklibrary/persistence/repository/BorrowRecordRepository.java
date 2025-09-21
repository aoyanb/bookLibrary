package com.assessment.booklibrary.persistence.repository;

import com.assessment.booklibrary.persistence.entity.BorrowRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecordEntity, Long> {
    List<BorrowRecordEntity> findByBookId(Long bookId);

    Optional<BorrowRecordEntity> findByBorrowerIdAndIsbn(Long borrowerId, String isbn);

    void deleteByBorrowerIdAndIsbn(Long borrowerId, String isbn);
}
