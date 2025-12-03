package com.codeup.eventify.domain.ports.in.venues;

import java.util.List;

import com.codeup.eventify.domain.model.Venue;

public interface RetrieveVenueUseCase {
    Venue getVenueById(Long id);

    List<Venue> getAllVenues();
}
