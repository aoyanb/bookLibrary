package com.assessment.booklibrary.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorReason {
    FIELD_VALIDATION_ERROR("Field validation error"),
    DUPLICATE_ISBN_NOT_ALLOWED("ISBN should be unique for different title and author"),
    EMAIL_ALREADY_EXISTS("Borrower with provided email already exist"),
    BORROWER_NOT_FOUND("Borrower not found for provided id"),
    BOOK_NOT_FOUND("Book not found for provided id"),
    BOOK_NOT_AVAILABLE("Requested book is not available now"),
    BORROW_RECORD_NOT_FOUND("Borrow record not found"),
    BORROW_SAME_BOOK_NOT_ALLOWED("User not allowed to borrow same book"),

    ;

    private final String message;
}
