package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateIsbnException extends BaseApiException {

    @Serial
    private static final long serialVersionUID = -3332331230983545035L;

    public DuplicateIsbnException(HttpStatus status, ErrorReason reason) {
        super(status, reason);
    }

    public static DuplicateIsbnException notAllowed(ErrorReason reason) {
        return new DuplicateIsbnException(HttpStatus.FORBIDDEN, reason);
    }
}
