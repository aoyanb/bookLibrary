package com.assessment.booklibrary.service.impl;

import com.assessment.booklibrary.api.error.ErrorReason;
import com.assessment.booklibrary.api.mapper.EntityMappers;
import com.assessment.booklibrary.api.model.BorrowRecord;
import com.assessment.booklibrary.api.model.BorrowRequest;
import com.assessment.booklibrary.api.model.ReturnRequest;
import com.assessment.booklibrary.exception.BookNotAvailableException;
import com.assessment.booklibrary.persistence.entity.BookEntity;
import com.assessment.booklibrary.persistence.entity.BorrowRecordEntity;
import com.assessment.booklibrary.persistence.repository.BookRepository;
import com.assessment.booklibrary.persistence.repository.BorrowRecordRepository;
import com.assessment.booklibrary.service.BorrowRecordService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Nonnull
    private final BorrowRecordRepository borrowRecordRepository;
    @Nonnull
    private final BookRepository bookRepository;
    @Nonnull
    private final EntityMappers mappers;

    @Override
    public boolean isRequestedBookNotAvailable(Long bookId) {
        return !borrowRecordRepository.findByBookId(bookId).isEmpty();
    }

    @Transactional
    @Override
    public BorrowRecord recordBorrow(BorrowRequest request) {
        BookEntity bookEntity = bookRepository.findById(request.getBookId()).orElse(null);
        if (bookEntity != null && borrowRecordRepository.findByBookId(request.getBookId()).isEmpty()) {
            BorrowRecordEntity savedEntity = borrowRecordRepository.save(BorrowRecordEntity.builder()
                    .borrowerId(request.getBorrowerId())
                    .bookId(bookEntity.getId())
                    .isbn(bookEntity.getIsbn())
                    .build());
            return mappers.fromEntity(savedEntity);
        } else {
            throw BookNotAvailableException.notAvailable(ErrorReason.BOOK_NOT_AVAILABLE);
        }
    }

    @Override
    public Optional<BorrowRecord> findBorrowRecord(Long borrowerId, String isbn) {
        return borrowRecordRepository.findByBorrowerIdAndIsbn(borrowerId, isbn).map(mappers::fromEntity);
    }

    @Transactional
    @Override
    public void returnBook(ReturnRequest request) {
        borrowRecordRepository.deleteByBorrowerIdAndIsbn(request.getBorrowerId(), request.getIsbn());
    }
}
