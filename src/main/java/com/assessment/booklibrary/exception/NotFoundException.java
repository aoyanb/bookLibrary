package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ErrorReason;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class NotFoundException extends BaseApiException {
    @Serial
    private static final long serialVersionUID = 5949266068775267168L;
    private final Long refId;

    public NotFoundException(HttpStatus status, ErrorReason reason) {
        super(status, reason);
        this.refId = null;
    }

    public NotFoundException(HttpStatus status, ErrorReason reason, Long refId) {
        super(status, reason);
        this.refId = refId;
    }

    public static NotFoundException notFound(ErrorReason reason) {
        return new NotFoundException(HttpStatus.NOT_FOUND, reason);
    }
}
