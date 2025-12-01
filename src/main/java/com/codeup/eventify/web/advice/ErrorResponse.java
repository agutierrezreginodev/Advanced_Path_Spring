package com.codeup.eventify.web.advice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error responses structure")
public class ErrorResponse {

    @Schema(description = "Error's timestamp", example = "2025-10-28T10:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Type of error", example = "Not Found")
    private String error;

    @Schema(description = "Error's message", example = "Venue not found with ID: 1")
    private String message;

    @Schema(description = "Error's endpoint", example = "/api/venues/1")
    private String path;

    @Schema(description = "Error's additional details")
    private List<String> details;
}