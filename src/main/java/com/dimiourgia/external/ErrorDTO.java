package com.dimiourgia.external;

import com.dimiourgia.abstracts.exception.MsErrorException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorDTO {
    int statusCode;
    Object message;
    String error;

    public ErrorDTO(MsErrorException e) {
        this.statusCode = e.getStatusCode().value();
        this.message = e.getMessage();
        this.error = e.getError();
    }
}
