package com.dimiourgia.abstracts.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MsErrorException extends RuntimeException {
    HttpStatusCode statusCode;
    String message;
    String error;

    public MsErrorException(final HttpStatusCode statusCode, final String message, final String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }

    public MsErrorException(final int statusCode, final String message, final String error) {
        this.statusCode = HttpStatusCode.valueOf(statusCode);
        this.message = message;
        this.error = error;
    }
}
