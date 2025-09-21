package com.assessment.booklibrary.api.controller;

import com.assessment.booklibrary.api.error.ErrorReason;
import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BorrowRecord;
import com.assessment.booklibrary.api.model.BorrowRequest;
import com.assessment.booklibrary.api.model.ReturnRequest;
import com.assessment.booklibrary.exception.*;
import com.assessment.booklibrary.service.BookService;
import com.assessment.booklibrary.service.BorrowRecordService;
import com.assessment.booklibrary.service.BorrowerService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/library")
@RestController
@RequiredArgsConstructor
public class LibraryController {
    @Nonnull
    private final BookService bookService;
    @Nonnull
    private final BorrowerService borrowerService;
    @Nonnull
    private final BorrowRecordService borrowRecordService;

    @PostMapping("/borrow")
    public Book borrowBook(@RequestBody BorrowRequest borrowRequest) {
        if (borrowerService.findBorrower(borrowRequest.getBorrowerId()).isEmpty()) {
            throw BorrowerNotFoundException.notFound(ErrorReason.BORROWER_NOT_FOUND, borrowRequest.getBorrowerId());
        }
        Book book = bookService.findBook(borrowRequest.getBookId()).orElse(null);
        if (book == null) {
            throw BorrowerNotFoundException.notFound(ErrorReason.BOOK_NOT_FOUND, borrowRequest.getBookId());
        }
        if (borrowRecordService.findBorrowRecord(borrowRequest.getBorrowerId(), book.getIsbn()).isPresent()) {
            throw BaseApiException.forbidden(ErrorReason.BORROW_SAME_BOOK_NOT_ALLOWED);
        }
        if (borrowRecordService.isRequestedBookNotAvailable(borrowRequest.getBookId())) {
            throw BookNotAvailableException.notAvailable(ErrorReason.BOOK_NOT_AVAILABLE);
        }
        BorrowRecord borrowRecord = borrowRecordService.recordBorrow(borrowRequest);
        return bookService.findBook(borrowRecord.getBookId()).orElseThrow(() -> BookNotFoundException.notFound(ErrorReason.BOOK_NOT_FOUND, borrowRecord.getBookId()));
    }

    @PostMapping("/return")
    public void returnBook(@RequestBody ReturnRequest request) {
        if (borrowRecordService.findBorrowRecord(request.getBorrowerId(), request.getIsbn()).isEmpty()) {
            throw NotFoundException.notFound(ErrorReason.BORROW_RECORD_NOT_FOUND);
        }
        borrowRecordService.returnBook(request);
    }
}
