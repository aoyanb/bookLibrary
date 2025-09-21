package com.assessment.booklibrary.exception;


import com.assessment.booklibrary.api.error.ErrorReason;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class BaseApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5498510266114118556L;
    private final HttpStatus status;
    private final ErrorReason reason;

    public BaseApiException(HttpStatus status, ErrorReason reason) {
        super();
        this.status = status;
        this.reason = reason;
    }

    public static BaseApiException forbidden(ErrorReason reason) {
        return new BaseApiException(HttpStatus.FORBIDDEN, reason);
    }
}
