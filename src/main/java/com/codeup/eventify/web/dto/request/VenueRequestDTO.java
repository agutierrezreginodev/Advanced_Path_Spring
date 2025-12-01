package com.codeup.eventify.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Necessary data to create and/or update a venue", name = "VenueRequest")
public class VenueRequestDTO {

    @Schema(
            description = "Venue's name",
            example = "Madison Square Garden",
            requiredMode = RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    @Schema(
            description = "Venue's country",
            example = "USA",
            requiredMode = RequiredMode.REQUIRED
    )
    @NotBlank(message = "Country is required")
    private String country;

    @Schema(
            description = "Venue's address",
            example = "4 Pennsylvania Plaza",
            requiredMode = RequiredMode.REQUIRED
    )
    @NotBlank(message = "Address is required")
    private String address;

    @Schema(
            description = "Venue's city",
            example = "New York",
            requiredMode = RequiredMode.REQUIRED
    )
    @NotBlank(message = "City is required")
    private String city;

    @Schema(
            description = "Venue's state",
            example = "NY"
    )
    private String state;

    @Schema(
            description = "Venue's zipcode",
            example = "10001",
            requiredMode = RequiredMode.REQUIRED
    )
    @NotBlank(message = "Zip code is required")
    private String zipCode;
}
