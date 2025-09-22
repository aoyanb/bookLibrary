package com.assessment.booklibrary.api.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiError {
    private ErrorReason reason;
    private Long refObjectId;

    public ApiError(@Nonnull ErrorReason reason) {
        this.reason = reason;
        this.refObjectId = null;
    }

    public ApiError(@Nonnull ErrorReason reason, @Nonnull Long refObjectId) {
        this.reason = reason;
        this.refObjectId = refObjectId;
    }
}
