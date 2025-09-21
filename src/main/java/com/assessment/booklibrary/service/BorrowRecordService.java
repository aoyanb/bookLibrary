package com.assessment.booklibrary.service;

import com.assessment.booklibrary.api.model.BorrowRecord;
import com.assessment.booklibrary.api.model.BorrowRequest;
import com.assessment.booklibrary.api.model.ReturnRequest;

import java.util.Optional;

public interface BorrowRecordService {
    boolean isRequestedBookNotAvailable(Long bookId);
    BorrowRecord recordBorrow(BorrowRequest request);
    Optional<BorrowRecord> findBorrowRecord(Long borrowerId, String isbn);
    void returnBook(ReturnRequest request);
}
