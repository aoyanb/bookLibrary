package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BookNotAvailableException extends BaseApiException {

    @Serial
    private static final long serialVersionUID = -5160385962602391084L;

    public BookNotAvailableException(HttpStatus status, ErrorReason reason) {
        super(status, reason);
    }

    public static BookNotAvailableException notAvailable(ErrorReason reason) {
        return new BookNotAvailableException(HttpStatus.CONFLICT, reason);
    }
}
