package com.assessment.booklibrary.api.error;


import lombok.*;

import java.util.Map;

@Getter
@AllArgsConstructor
public class FieldValidationError {
    private Map<String, String> validationErrors;
    private ErrorReason errorReason;
}
