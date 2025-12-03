package com.codeup.eventify.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenueTest {

    @Test
    void builder_ShouldCreateVenueWithAllFields() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        assertEquals(1L, venue.getId());
        assertEquals("Madison Square Garden", venue.getName());
        assertEquals("USA", venue.getCountry());
        assertEquals("4 Pennsylvania Plaza", venue.getAddress());
        assertEquals("New York", venue.getCity());
        assertEquals("NY", venue.getState());
        assertEquals("10001", venue.getZipCode());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        Venue venue = new Venue();

        venue.setId(2L);
        venue.setName("Staples Center");
        venue.setCountry("USA");
        venue.setAddress("1111 S Figueroa St");
        venue.setCity("Los Angeles");
        venue.setState("CA");
        venue.setZipCode("90015");

        assertEquals(2L, venue.getId());
        assertEquals("Staples Center", venue.getName());
        assertEquals("USA", venue.getCountry());
        assertEquals("1111 S Figueroa St", venue.getAddress());
        assertEquals("Los Angeles", venue.getCity());
        assertEquals("CA", venue.getState());
        assertEquals("90015", venue.getZipCode());
    }

    @Test
    void equals_ShouldReturnTrueForSameVenue() {
        Venue venue1 = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        Venue venue2 = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        assertEquals(venue1, venue2);
        assertEquals(venue1.hashCode(), venue2.hashCode());
    }

    @Test
    void equals_ShouldReturnFalseForDifferentVenues() {
        Venue venue1 = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        Venue venue2 = Venue.builder()
                .id(2L)
                .name("Staples Center")
                .country("USA")
                .address("1111 S Figueroa St")
                .city("Los Angeles")
                .state("CA")
                .zipCode("90015")
                .build();

        assertNotEquals(venue1, venue2);
    }

    @Test
    void toString_ShouldReturnFormattedString() {
        Venue venue = Venue.builder()
                .id(1L)
                .name("Madison Square Garden")
                .country("USA")
                .address("4 Pennsylvania Plaza")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .build();

        String toString = venue.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name='Madison Square Garden'"));
        assertTrue(toString.contains("country='USA'"));
        assertTrue(toString.contains("address='4 Pennsylvania Plaza'"));
        assertTrue(toString.contains("city='New York'"));
        assertTrue(toString.contains("state='NY'"));
        assertTrue(toString.contains("zipCode='10001'"));
    }
}
