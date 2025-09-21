package com.assessment.booklibrary.exception;

import com.assessment.booklibrary.api.error.ApiError;
import com.assessment.booklibrary.api.error.ErrorReason;
import com.assessment.booklibrary.api.error.FieldValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FieldValidationError handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new FieldValidationError(validationErrors, ErrorReason.FIELD_VALIDATION_ERROR);
    }

    @ExceptionHandler(BaseApiException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleApiException(BaseApiException ex) {
        return new ApiError(ex.getReason());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleApiException(NotFoundException ex) {
        return new ApiError(ex.getReason(), ex.getRefId());
    }
}
