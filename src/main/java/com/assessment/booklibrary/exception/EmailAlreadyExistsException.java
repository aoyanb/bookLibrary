package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class EmailAlreadyExistsException extends BaseApiException {

    @Serial
    private static final long serialVersionUID = -9198622074315854925L;

    public EmailAlreadyExistsException(HttpStatus status, ErrorReason reason) {
        super(status, reason);
    }

    public static EmailAlreadyExistsException notAllowed(ErrorReason reason) {
        return new EmailAlreadyExistsException(HttpStatus.FORBIDDEN, reason);
    }
}
