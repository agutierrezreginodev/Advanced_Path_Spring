package com.codeup.eventify.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Complete information of an event with its associated venue", name = "EventResponse")
public class EventResponseDTO {

    @Schema(description = "Event's ID", example = "1")
    private Long id;

    @Schema(description = "Event's title", example = "Rock Concert 2025")
    private String title;

    @Schema(description = "Event's description", example = "Amazing rock concert with live bands")
    private String description;

    @Schema(description = "Venue's information")
    private VenueResponseDTO venue;

    @Schema(description = "Event's date", example = "2025-12-15")
    private String date;

    @Schema(description = "Event's hour", example = "2025-12-15T20:00:00")
    private LocalTime hour;

    @Schema(description = "Event's price", example = "550.00")
    private Double price;

    @Schema(description = "Event's hoster", example = "Live Nation")
    private String hostedBy;
}
