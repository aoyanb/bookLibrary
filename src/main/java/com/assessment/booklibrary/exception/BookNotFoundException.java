package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BookNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -3350792618703031751L;

    public BookNotFoundException(HttpStatus status, ErrorReason reason, Long refId) {
        super(status, reason, refId);
    }

    public static BookNotFoundException notFound(ErrorReason reason, Long refId) {
        return new BookNotFoundException(HttpStatus.NOT_FOUND, reason, refId);
    }
}
