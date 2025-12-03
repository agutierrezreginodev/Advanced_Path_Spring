package com.codeup.eventify.infrastructure.adapters.in.web;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.ports.in.events.*;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.request.EventRequestDTO;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.response.EventResponseDTO;

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
public class EventRestAdapter {

        private final CreateEventUseCase createEventUseCase;
        private final RetrieveEventUseCase retrieveEventUseCase;
        private final UpdateEventUseCase updateEventUseCase;
        private final DeleteEventUseCase deleteEventUseCase;
        private final VenueRepositoryPort venueRepository;

        @Operation(summary = "Create a new event", description = "Create an event with an existing associated venue")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Event created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "404", description = "Venue not found")
        })
        @PostMapping
        public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
                Event event = toEvent(requestDTO);
                Event createdEvent = createEventUseCase.createEvent(event);
                EventResponseDTO responseDTO = toResponseDTO(createdEvent);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }

        @Operation(summary = "Get event by ID", description = "Retrieve a specific event by its identifier")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Event found successfully"),
                        @ApiResponse(responseCode = "404", description = "Event not found")
        })
        @GetMapping("/{id}")
        public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
                Event event = retrieveEventUseCase.getEventById(id);
                EventResponseDTO responseDTO = toResponseDTO(event);
                return ResponseEntity.ok(responseDTO);
        }

        @Operation(summary = "Get all events", description = "Retrieve a list of all available events")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Events retrieved successfully")
        })
        @GetMapping
        public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
                List<Event> events = retrieveEventUseCase.getAllEvents();
                List<EventResponseDTO> responseDTOs = events.stream()
                                .map(this::toResponseDTO)
                                .toList();
                return ResponseEntity.ok(responseDTOs);
        }

        @Operation(summary = "Get events by venue", description = "Retrieve all events associated with a specific venue")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Events retrieved successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid venue ID")
        })
        @GetMapping("/venue/{venueId}")
        public ResponseEntity<List<EventResponseDTO>> getEventsByVenueId(@PathVariable Long venueId) {
                List<Event> events = retrieveEventUseCase.getEventsByVenueId(venueId);
                List<EventResponseDTO> responseDTOs = events.stream()
                                .map(this::toResponseDTO)
                                .toList();
                return ResponseEntity.ok(responseDTOs);
        }

        @Operation(summary = "Update an event", description = "Update an existing event's information")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Event updated successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "404", description = "Event or venue not found")
        })
        @PutMapping("/{id}")
        public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id,
                        @Valid @RequestBody EventRequestDTO requestDTO) {
                Event event = toEvent(requestDTO);
                Event updatedEvent = updateEventUseCase.updateEvent(id, event);
                EventResponseDTO responseDTO = toResponseDTO(updatedEvent);

                return ResponseEntity.ok(responseDTO);
        }

        @Operation(summary = "Delete an event", description = "Remove an event from the system")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
                        @ApiResponse(responseCode = "404", description = "Event not found")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
                deleteEventUseCase.deleteEvent(id);
                return ResponseEntity.noContent().build();
        }

        private Event toEvent(EventRequestDTO requestDTO) {
                // Resolve venue using venueId from request
                var venue = venueRepository.findById(requestDTO.getVenueId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Venue not found with ID: " + requestDTO.getVenueId()));

                return Event.builder()
                                .title(requestDTO.getTitle())
                                .description(requestDTO.getDescription())
                                .date(requestDTO.getDate())
                                .venue(venue)
                                .hour(requestDTO.getHour())
                                .price(requestDTO.getPrice())
                                .hostedBy(requestDTO.getHostedBy())
                                .build();
        }

        private EventResponseDTO toResponseDTO(Event event) {
                return EventResponseDTO.builder()
                                .id(event.getId())
                                .title(event.getTitle())
                                .description(event.getDescription())
                                .venue(toVenueResponseDTO(event.getVenue()))
                                .date(event.getDate())
                                .hour(event.getHour())
                                .price(event.getPrice())
                                .hostedBy(event.getHostedBy())
                                .build();
        }

        private com.codeup.eventify.infrastructure.adapters.in.web.dto.response.VenueResponseDTO toVenueResponseDTO(
                        com.codeup.eventify.domain.model.Venue venue) {
                if (venue == null) {
                        return null;
                }
                return com.codeup.eventify.infrastructure.adapters.in.web.dto.response.VenueResponseDTO.builder()
                                .id(venue.getId())
                                .name(venue.getName())
                                .country(venue.getCountry())
                                .address(venue.getAddress())
                                .city(venue.getCity())
                                .state(venue.getState())
                                .zipCode(venue.getZipCode())
                                .build();
        }
}
