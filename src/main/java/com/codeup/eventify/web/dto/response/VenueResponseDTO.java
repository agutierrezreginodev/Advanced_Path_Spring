package com.codeup.eventify.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Venue's complete information", name = "VenueResponse")
public class VenueResponseDTO {

    @Schema(description = "Venue's ID", example = "1")
    private Long id;

    @Schema(description = "Venue's name", example = "Madison Square Garden")
    private String name;

    @Schema(description = "Country", example = "USA")
    private String country;

    @Schema(description = "Address", example = "4 Pennsylvania Plaza")
    private String address;

    @Schema(description = "City", example = "New York")
    private String city;

    @Schema(description = "State", example = "NY")
    private String state;

    @Schema(description = "ZIP Code", example = "10001")
    private String zipCode;
}

