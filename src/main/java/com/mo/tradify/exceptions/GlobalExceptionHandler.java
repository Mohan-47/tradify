package com.mo.tradify.exceptions;

import com.mo.tradify.domain.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebsocketConnectionException.class)
    public ResponseEntity<ErrorDto> handleWebsocketConnectionException(WebsocketConnectionException e)  {
        log.error("Websocket Connection Exception: {}", e.getMessage(), e);
        ErrorDto errorDto = new ErrorDto(
            "Websocket connection Failed",
            e.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        ErrorDto errorDto = new ErrorDto(
            "Internal Server error",
            e.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
