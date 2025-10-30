package com.codeup.eventify.web.controller;

import com.codeup.eventify.web.dto.request.VenueRequestDTO;
import com.codeup.eventify.web.dto.response.VenueResponseDTO;
import com.codeup.eventify.service.VenueService;
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
@Tag(name = "Venues", description = "API for venue management (event locations)")
public class VenueController {

    private final VenueService venueService;

    @Operation(summary = "Create a new venue", description = "Create a new venue with the provided info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venue created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<VenueResponseDTO> createVenue(@Valid @RequestBody VenueRequestDTO requestDTO) {
        VenueResponseDTO response = venueService.createVenue(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a venue with its ID", description = "Return an specific venue due to its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> getVenueById(@PathVariable Long id) {
        VenueResponseDTO response = venueService.getVenueById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List all venues", description = "Return a list with all registered venues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venues list obtained successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAllVenues() {
        List<VenueResponseDTO> response = venueService.getAllVenues();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a venue", description = "Update an existing venue's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> updateVenue(
            @PathVariable Long id,
            @Valid @RequestBody VenueRequestDTO requestDTO) {
        VenueResponseDTO response = venueService.updateVenue(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a venue", description = "Delete a venue due to its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venue deleted successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}