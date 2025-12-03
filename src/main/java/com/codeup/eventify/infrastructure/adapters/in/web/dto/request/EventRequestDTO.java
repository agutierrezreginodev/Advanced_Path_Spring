package com.codeup.eventify.infrastructure.adapters.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Necessary data to create and/or update an event", name = "EventRequest")
public class EventRequestDTO {

        @Schema(description = "Event's title", example = "Rock Concert 2025", requiredMode = RequiredMode.REQUIRED)
        @NotBlank(message = "Title is required")
        private String title;

        @Schema(description = "Event's detailed description", example = "Amazing rock concert with live bands", requiredMode = RequiredMode.REQUIRED)
        @NotBlank(message = "Description is required")
        private String description;

        @Schema(description = "Venue's ID where the event will be located", example = "1", requiredMode = RequiredMode.REQUIRED)
        @NotNull(message = "Venue ID is required")
        private Long venueId;

        @Schema(description = "Event's date (formato: YYYY-MM-DD)", example = "2025-12-15", requiredMode = RequiredMode.REQUIRED)
        @NotBlank(message = "Date is required")
        private String date;

        @Schema(description = "Event's hour", example = "2025-12-15T20:00:00", requiredMode = RequiredMode.REQUIRED)
        @NotNull(message = "Hour is required")
        private LocalTime hour;

        @Schema(description = "Event's price", example = "550.00", requiredMode = RequiredMode.REQUIRED)
        @NotNull(message = "Price is required")
        private Double price;

        @Schema(description = "Event's hoster", example = "Live Nation", requiredMode = RequiredMode.REQUIRED)
        @NotBlank(message = "Hosted by is required")
        private String hostedBy;
}
