package com.zhandabo.overgame.util;

import com.zhandabo.overgame.exception.OvergameException;
import org.springframework.http.HttpStatus;

public class OvergameExceptionUtils {

    public static OvergameException badRequest(String message) {
        return new OvergameException(HttpStatus.BAD_REQUEST, message, message);
    }

    public static OvergameException internalServerError(String message) {
        return new OvergameException(HttpStatus.INTERNAL_SERVER_ERROR, message, message);
    }

    public static OvergameException internalServerError(String message, Object... params) {
        return new OvergameException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(message, params), String.format(message, params));
    }
}
