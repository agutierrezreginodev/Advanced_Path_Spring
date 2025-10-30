package com.codeup.eventify.web.controller;

import com.codeup.eventify.web.dto.request.EventRequestDTO;
import com.codeup.eventify.web.dto.response.EventResponseDTO;
import com.codeup.eventify.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Events Management API")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Create a new event", description = "Create an event with an existing associated venue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO response = eventService.createEvent(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get an event due to its ID", description = "Return an specific event with all its information including the associated venue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        EventResponseDTO response = eventService.getEventById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List all events", description = "Return a list with all registered events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events list obtained successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> response = eventService.getAllEvents();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List events due to its venue", description = "Return all the events associated to an specific venue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events list obtained successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    })
    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<EventResponseDTO>> getEventsByVenueId(@PathVariable Long venueId) {
        List<EventResponseDTO> response = eventService.getEventsByVenueId(venueId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an event", description = "Update an existing event's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO response = eventService.updateEvent(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete an event", description = "Delete an event due to its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event delete successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}