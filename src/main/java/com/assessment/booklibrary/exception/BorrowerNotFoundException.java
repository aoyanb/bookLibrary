package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BorrowerNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 6515942171336022786L;

    public BorrowerNotFoundException(HttpStatus status, ErrorReason reason, Long refId) {
        super(status, reason, refId);
    }

    public static BorrowerNotFoundException notFound(ErrorReason reason, Long refId) {
        return new BorrowerNotFoundException(HttpStatus.NOT_FOUND, reason, refId);
    }
}
