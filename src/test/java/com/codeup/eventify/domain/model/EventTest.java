package com.codeup.eventify.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void builder_ShouldCreateEventWithAllFields() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        Event event = Event.builder()
                .id(1L)
                .title("Rock Concert 2025")
                .description("Amazing rock concert with live bands")
                .date("2025-12-15")
                .venue(venue)
                .hour(LocalTime.of(20, 0))
                .price(550.00)
                .hostedBy("Live Nation")
                .build();

        assertEquals(1L, event.getId());
        assertEquals("Rock Concert 2025", event.getTitle());
        assertEquals("Amazing rock concert with live bands", event.getDescription());
        assertEquals("2025-12-15", event.getDate());
        assertEquals(venue, event.getVenue());
        assertEquals(LocalTime.of(20, 0), event.getHour());
        assertEquals(550.00, event.getPrice());
        assertEquals("Live Nation", event.getHostedBy());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        Event event = new Event();
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .build();

        event.setId(2L);
        event.setTitle("Jazz Night");
        event.setDescription("Smooth jazz evening");
        event.setDate("2025-12-20");
        event.setVenue(venue);
        event.setHour(LocalTime.of(19, 0));
        event.setPrice(75.00);
        event.setHostedBy("Jazz Club");

        assertEquals(2L, event.getId());
        assertEquals("Jazz Night", event.getTitle());
        assertEquals("Smooth jazz evening", event.getDescription());
        assertEquals("2025-12-20", event.getDate());
        assertEquals(venue, event.getVenue());
        assertEquals(LocalTime.of(19, 0), event.getHour());
        assertEquals(75.00, event.getPrice());
        assertEquals("Jazz Club", event.getHostedBy());
    }

    @Test
    void equals_ShouldReturnTrueForSameEvent() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .build();

        Event event1 = Event.builder()
                .id(1L)
                .title("Rock Concert 2025")
                .description("Amazing rock concert with live bands")
                .date("2025-12-15")
                .venue(venue)
                .hour(LocalTime.of(20, 0))
                .price(550.00)
                .hostedBy("Live Nation")
                .build();

        Event event2 = Event.builder()
                .id(1L)
                .title("Rock Concert 2025")
                .description("Amazing rock concert with live bands")
                .date("2025-12-15")
                .venue(venue)
                .hour(LocalTime.of(20, 0))
                .price(550.00)
                .hostedBy("Live Nation")
                .build();

        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void equals_ShouldReturnFalseForDifferentEvents() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .build();

        Event event1 = Event.builder()
                .id(1L)
                .title("Rock Concert 2025")
                .description("Amazing rock concert with live bands")
                .date("2025-12-15")
                .venue(venue)
                .hour(LocalTime.of(20, 0))
                .price(550.00)
                .hostedBy("Live Nation")
                .build();

        Event event2 = Event.builder()
                .id(2L)
                .title("Jazz Night")
                .description("Smooth jazz evening")
                .date("2025-12-20")
                .venue(venue)
                .hour(LocalTime.of(19, 0))
                .price(75.00)
                .hostedBy("Jazz Club")
                .build();

        assertNotEquals(event1, event2);
    }

    @Test
    void toString_ShouldReturnFormattedString() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .build();

        Event event = Event.builder()
                .id(1L)
                .title("Rock Concert 2025")
                .description("Amazing rock concert")
                .date("2025-12-15")
                .venue(venue)
                .hour(LocalTime.of(20, 0))
                .price(550.00)
                .hostedBy("Live Nation")
                .build();

        String toString = event.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("title='Rock Concert 2025'"));
        assertTrue(toString.contains("description='Amazing rock concert'"));
        assertTrue(toString.contains("date='2025-12-15'"));
        assertTrue(toString.contains("hour=20:00"));
        assertTrue(toString.contains("price=550.0"));
        assertTrue(toString.contains("hostedBy='Live Nation'"));
    }
}
