package com.codeup.eventify.domain.ports.in.venues;

import com.codeup.eventify.domain.model.Venue;

public interface UpdateVenueUseCase {
    Venue updateVenue(Long id, Venue venue);
}
