package com.codeup.eventify.infrastructure.adapters.in.web;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.events.CreateEventUseCase;
import com.codeup.eventify.domain.ports.in.events.DeleteEventUseCase;
import com.codeup.eventify.domain.ports.in.events.RetrieveEventUseCase;
import com.codeup.eventify.domain.ports.in.events.UpdateEventUseCase;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;
import com.codeup.eventify.infrastructure.adapters.in.web.dto.request.EventRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Note: @MockBean is deprecated in Spring Boot 3.4.0+ but kept for compatibility with current version
// In future versions, consider migrating to @MockitoBean or other mocking strategies

@WebMvcTest(EventRestAdapter.class)
class EventRestAdapterTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private CreateEventUseCase createEventUseCase;

        @MockBean
        private RetrieveEventUseCase retrieveEventUseCase;

        @MockBean
        private UpdateEventUseCase updateEventUseCase;

        @MockBean
        private DeleteEventUseCase deleteEventUseCase;

        @MockBean
        private VenueRepositoryPort venueRepository;

        private Event testEvent;
        private Venue testVenue;
        private EventRequestDTO eventRequestDTO;

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

                testEvent = Event.builder()
                                .id(1L)
                                .title("Rock Concert 2025")
                                .description("Amazing rock concert with live bands")
                                .date("2025-12-15")
                                .venue(testVenue)
                                .hour(LocalTime.of(20, 0))
                                .price(550.00)
                                .hostedBy("Live Nation")
                                .build();

                eventRequestDTO = EventRequestDTO.builder()
                                .title("Rock Concert 2025")
                                .description("Amazing rock concert with live bands")
                                .venueId(1L)
                                .date("2025-12-15")
                                .hour(LocalTime.of(20, 0))
                                .price(550.00)
                                .hostedBy("Live Nation")
                                .build();

                // Mock venue repository behavior
                when(venueRepository.findById(1L)).thenReturn(java.util.Optional.of(testVenue));
        }

        @Test
        void createEvent_ShouldReturnCreatedEvent() throws Exception {
                when(createEventUseCase.createEvent(any(Event.class))).thenReturn(testEvent);

                mockMvc.perform(post("/api/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventRequestDTO)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.title").value("Rock Concert 2025"))
                                .andExpect(jsonPath("$.description").value("Amazing rock concert with live bands"))
                                .andExpect(jsonPath("$.date").value("2025-12-15"))
                                .andExpect(jsonPath("$.hour").value("20:00:00"))
                                .andExpect(jsonPath("$.price").value(550.00))
                                .andExpect(jsonPath("$.hostedBy").value("Live Nation"))
                                .andExpect(jsonPath("$.venue.id").value(1))
                                .andExpect(jsonPath("$.venue.name").value("Madison Square Garden"));

                verify(createEventUseCase, times(1)).createEvent(any(Event.class));
        }

        @Test
        void createEvent_ShouldReturnBadRequest_WhenInvalidData() throws Exception {
                EventRequestDTO invalidRequest = EventRequestDTO.builder()
                                .title("")
                                .description("")
                                .venueId(null)
                                .date("")
                                .hour(null)
                                .price(null)
                                .hostedBy("")
                                .build();

                mockMvc.perform(post("/api/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                                .andExpect(status().isBadRequest());

                verify(createEventUseCase, never()).createEvent(any(Event.class));
        }

        @Test
        void getEventById_ShouldReturnEvent() throws Exception {
                when(retrieveEventUseCase.getEventById(1L)).thenReturn(testEvent);

                mockMvc.perform(get("/api/events/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.title").value("Rock Concert 2025"))
                                .andExpect(jsonPath("$.venue.name").value("Madison Square Garden"));

                verify(retrieveEventUseCase, times(1)).getEventById(1L);
        }

        @Test
        void getAllEvents_ShouldReturnListOfEvents() throws Exception {
                Event event2 = Event.builder()
                                .id(2L)
                                .title("Jazz Night")
                                .description("Smooth jazz evening")
                                .date("2025-12-20")
                                .venue(testVenue)
                                .hour(LocalTime.of(19, 0))
                                .price(75.00)
                                .hostedBy("Jazz Club")
                                .build();

                List<Event> events = Arrays.asList(testEvent, event2);
                when(retrieveEventUseCase.getAllEvents()).thenReturn(events);

                mockMvc.perform(get("/api/events"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].title").value("Rock Concert 2025"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].title").value("Jazz Night"));

                verify(retrieveEventUseCase, times(1)).getAllEvents();
        }

        @Test
        void getEventsByVenueId_ShouldReturnEventsForVenue() throws Exception {
                List<Event> events = Arrays.asList(testEvent);
                when(retrieveEventUseCase.getEventsByVenueId(1L)).thenReturn(events);

                mockMvc.perform(get("/api/events/venue/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].title").value("Rock Concert 2025"))
                                .andExpect(jsonPath("$[0].venue.id").value(1));

                verify(retrieveEventUseCase, times(1)).getEventsByVenueId(1L);
        }

        @Test
        void updateEvent_ShouldReturnUpdatedEvent() throws Exception {
                Event updatedEvent = Event.builder()
                                .id(1L)
                                .title("Updated Rock Concert 2025")
                                .description("Updated amazing rock concert")
                                .date("2025-12-16")
                                .venue(testVenue)
                                .hour(LocalTime.of(21, 0))
                                .price(600.00)
                                .hostedBy("Updated Live Nation")
                                .build();

                when(updateEventUseCase.updateEvent(eq(1L), any(Event.class))).thenReturn(updatedEvent);

                EventRequestDTO updateRequest = EventRequestDTO.builder()
                                .title("Updated Rock Concert 2025")
                                .description("Updated amazing rock concert")
                                .venueId(1L)
                                .date("2025-12-16")
                                .hour(LocalTime.of(21, 0))
                                .price(600.00)
                                .hostedBy("Updated Live Nation")
                                .build();

                mockMvc.perform(put("/api/events/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.title").value("Updated Rock Concert 2025"))
                                .andExpect(jsonPath("$.price").value(600.00));

                verify(updateEventUseCase, times(1)).updateEvent(eq(1L), any(Event.class));
        }

        @Test
        void deleteEvent_ShouldReturnNoContent() throws Exception {
                doNothing().when(deleteEventUseCase).deleteEvent(1L);

                mockMvc.perform(delete("/api/events/1"))
                                .andExpect(status().isNoContent());

                verify(deleteEventUseCase, times(1)).deleteEvent(1L);
        }
}
