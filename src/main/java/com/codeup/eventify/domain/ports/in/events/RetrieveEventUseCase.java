package com.codeup.eventify.domain.ports.in.events;

import java.util.List;

import com.codeup.eventify.domain.model.Event;

public interface RetrieveEventUseCase {
    Event getEventById(Long id);

    List<Event> getAllEvents();

    List<Event> getEventsByVenueId(Long venueId);
}
