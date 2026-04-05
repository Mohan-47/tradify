package com.mo.tradify.domain.dto;

import java.time.LocalDateTime;

public record ErrorDto(
    String error,
    String message,
    LocalDateTime timestamp
) {
}
