package com.codeup.eventify.infrastructure.adapters.in.web;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.venues.*;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.request.VenueRequestDTO;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.response.VenueResponseDTO;

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
@RequestMapping("/api/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "Venues Management API")
public class VenueRestAdapter {

        private final CreateVenueUseCase createVenueUseCase;
        private final RetrieveVenueUseCase retrieveVenueUseCase;
        private final DeleteVenueUseCase deleteVenueUseCase;
        private final UpdateVenueUseCase updateVenueUseCase;

        @Operation(summary = "Create a new venue", description = "Create a new venue with the provided information")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Venue created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "409", description = "Venue already exists")
        })
        @PostMapping
        public ResponseEntity<VenueResponseDTO> createVenue(@Valid @RequestBody VenueRequestDTO requestDTO) {
                Venue venue = toVenue(requestDTO);
                Venue createdVenue = createVenueUseCase.createVenue(venue);
                VenueResponseDTO responseDTO = toResponseDTO(createdVenue);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }

        @Operation(summary = "Get venue by ID", description = "Retrieve a specific venue by its identifier")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venue found successfully"),
                        @ApiResponse(responseCode = "404", description = "Venue not found")
        })
        @GetMapping("/{id}")
        public ResponseEntity<VenueResponseDTO> getVenueById(@PathVariable Long id) {
                Venue venue = retrieveVenueUseCase.getVenueById(id);
                VenueResponseDTO responseDTO = toResponseDTO(venue);
                return ResponseEntity.ok(responseDTO);
        }

        @Operation(summary = "Get all venues", description = "Retrieve a list of all available venues")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venues retrieved successfully")
        })
        @GetMapping
        public ResponseEntity<List<VenueResponseDTO>> getAllVenues() {
                List<Venue> venues = retrieveVenueUseCase.getAllVenues();
                List<VenueResponseDTO> responseDTOs = venues.stream()
                                .map(this::toResponseDTO)
                                .toList();
                return ResponseEntity.ok(responseDTOs);
        }

        @Operation(summary = "Update a venue", description = "Update an existing venue's information")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Venue updated successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "404", description = "Venue not found")
        })
        @PutMapping("/{id}")
        public ResponseEntity<VenueResponseDTO> updateVenue(@PathVariable Long id,
                        @Valid @RequestBody VenueRequestDTO requestDTO) {
                Venue venue = toVenue(requestDTO);
                Venue updatedVenue = updateVenueUseCase.updateVenue(id, venue);
                VenueResponseDTO responseDTO = toResponseDTO(updatedVenue);
                return ResponseEntity.ok(responseDTO);
        }

        @Operation(summary = "Delete a venue", description = "Remove a venue from the system")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Venue deleted successfully"),
                        @ApiResponse(responseCode = "404", description = "Venue not found")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
                deleteVenueUseCase.deleteVenue(id);
                return ResponseEntity.noContent().build();
        }

        private Venue toVenue(VenueRequestDTO requestDTO) {
                return Venue.builder()
                                .name(requestDTO.getName())
                                .country(requestDTO.getCountry())
                                .address(requestDTO.getAddress())
                                .city(requestDTO.getCity())
                                .state(requestDTO.getState())
                                .zipCode(requestDTO.getZipCode())
                                .build();
        }

        private VenueResponseDTO toResponseDTO(Venue venue) {
                return VenueResponseDTO.builder()
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
