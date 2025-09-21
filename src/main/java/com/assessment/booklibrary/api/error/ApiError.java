package com.assessment.booklibrary.api.error;


import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
public class ApiError {
    private final ErrorReason reason;
    private final Long refObjectId;

    public ApiError(@Nonnull ErrorReason reason) {
        this.reason = reason;
        this.refObjectId = null;
    }

    public ApiError(@Nonnull ErrorReason reason, @Nonnull Long refObjectId) {
        this.reason = reason;
        this.refObjectId = refObjectId;
    }
}
