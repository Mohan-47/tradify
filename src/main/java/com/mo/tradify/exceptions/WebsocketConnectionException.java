package com.mo.tradify.exceptions;

public class WebsocketConnectionException extends RuntimeException {
    public WebsocketConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
