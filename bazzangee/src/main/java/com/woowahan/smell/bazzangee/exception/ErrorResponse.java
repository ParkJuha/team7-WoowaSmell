package com.woowahan.smell.bazzangee.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ErrorResponse {
    private String message;

    private ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse ofString(String message) {
        return new ErrorResponse(message);
    }
}

