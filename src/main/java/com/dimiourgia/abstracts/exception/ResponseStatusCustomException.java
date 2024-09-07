package com.dimiourgia.abstracts.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResponseStatusCustomException extends ResponseStatusException {
    public ResponseStatusCustomException(
            HttpStatusCode status,
            String reason) {
        super(status, reason);
    }
}
