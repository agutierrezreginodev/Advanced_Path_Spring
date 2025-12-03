package com.codeup.eventify.infrastructure.adapters.in.web;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.venues.CreateVenueUseCase;
import com.codeup.eventify.domain.ports.in.venues.DeleteVenueUseCase;
import com.codeup.eventify.domain.ports.in.venues.RetrieveVenueUseCase;
import com.codeup.eventify.domain.ports.in.venues.UpdateVenueUseCase;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.request.VenueRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Note: @MockBean is deprecated in Spring Boot 3.4.0+ but kept for compatibility with current version
// In future versions, consider migrating to @MockitoBean or other mocking strategies

@WebMvcTest(VenueRestAdapter.class)
class VenueRestAdapterTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private CreateVenueUseCase createVenueUseCase;

        @MockBean
        private RetrieveVenueUseCase retrieveVenueUseCase;

        @MockBean
        private DeleteVenueUseCase deleteVenueUseCase;

        @MockBean
        private UpdateVenueUseCase updateVenueUseCase;

        private Venue testVenue;
        private VenueRequestDTO venueRequestDTO;

        @BeforeEach
        void setUp() {
                testVenue = Venue.builder()
                                .id(1L)
                                .name("Madison Square Garden")
                                .country("USA")
                                .address("4 Pennsylvania Plaza")
                                .city("New York")
                                .state("NY")
                                .zipCode("10001")
                                .build();

                venueRequestDTO = VenueRequestDTO.builder()
                                .name("Madison Square Garden")
                                .country("USA")
                                .address("4 Pennsylvania Plaza")
                                .city("New York")
                                .state("NY")
                                .zipCode("10001")
                                .build();
        }

        @Test
        void createVenue_ShouldReturnCreatedVenue() throws Exception {
                when(createVenueUseCase.createVenue(any(Venue.class))).thenReturn(testVenue);

                mockMvc.perform(post("/api/venues")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(venueRequestDTO)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("Madison Square Garden"))
                                .andExpect(jsonPath("$.country").value("USA"))
                                .andExpect(jsonPath("$.address").value("4 Pennsylvania Plaza"))
                                .andExpect(jsonPath("$.city").value("New York"))
                                .andExpect(jsonPath("$.state").value("NY"))
                                .andExpect(jsonPath("$.zipCode").value("10001"));

                verify(createVenueUseCase, times(1)).createVenue(any(Venue.class));
        }

        @Test
        void createVenue_ShouldReturnBadRequest_WhenInvalidData() throws Exception {
                VenueRequestDTO invalidRequest = VenueRequestDTO.builder()
                                .name("")
                                .country("")
                                .address("")
                                .city("")
                                .zipCode("")
                                .build();

                mockMvc.perform(post("/api/venues")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());

                verify(createVenueUseCase, never()).createVenue(any(Venue.class));
        }

        @Test
        void getVenueById_ShouldReturnVenue() throws Exception {
                when(retrieveVenueUseCase.getVenueById(1L)).thenReturn(testVenue);

                mockMvc.perform(get("/api/venues/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("Madison Square Garden"))
                                .andExpect(jsonPath("$.country").value("USA"));

                verify(retrieveVenueUseCase, times(1)).getVenueById(1L);
        }

        @Test
        void getAllVenues_ShouldReturnListOfVenues() throws Exception {
                Venue venue2 = Venue.builder()
                                .id(2L)
                                .name("Staples Center")
                                .country("USA")
                                .address("1111 S Figueroa St")
                                .city("Los Angeles")
                                .state("CA")
                                .zipCode("90015")
                                .build();

                List<Venue> venues = Arrays.asList(testVenue, venue2);
                when(retrieveVenueUseCase.getAllVenues()).thenReturn(venues);

                mockMvc.perform(get("/api/venues"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].name").value("Madison Square Garden"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].name").value("Staples Center"));

                verify(retrieveVenueUseCase, times(1)).getAllVenues();
        }

        @Test
        void updateVenue_ShouldReturnUpdatedVenue() throws Exception {
                Venue updatedVenue = Venue.builder()
                                .id(1L)
                                .name("Updated Madison Square Garden")
                                .country("USA")
                                .address("4 Pennsylvania Plaza")
                                .city("New York")
                                .state("NY")
                                .zipCode("10001")
                                .build();

                when(updateVenueUseCase.updateVenue(eq(1L), any(Venue.class))).thenReturn(updatedVenue);

                VenueRequestDTO updateRequest = VenueRequestDTO.builder()
                                .name("Updated Madison Square Garden")
                                .country("USA")
                                .address("4 Pennsylvania Plaza")
                                .city("New York")
                                .state("NY")
                                .zipCode("10001")
                                .build();

                mockMvc.perform(put("/api/venues/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("Updated Madison Square Garden"));

                verify(updateVenueUseCase, times(1)).updateVenue(eq(1L), any(Venue.class));
        }

        @Test
        void deleteVenue_ShouldReturnNoContent() throws Exception {
                doNothing().when(deleteVenueUseCase).deleteVenue(1L);

                mockMvc.perform(delete("/api/venues/1"))
                                .andExpect(status().isNoContent());

                verify(deleteVenueUseCase, times(1)).deleteVenue(1L);
        }
}
