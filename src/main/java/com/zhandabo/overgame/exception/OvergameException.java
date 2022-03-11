package com.zhandabo.overgame.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OvergameException extends RuntimeException {

    private final HttpStatus status;
    private final String code;
    private final String message;

    public OvergameException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
