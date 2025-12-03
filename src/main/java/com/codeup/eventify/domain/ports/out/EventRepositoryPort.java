package com.codeup.eventify.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.codeup.eventify.domain.model.Event;

public interface EventRepositoryPort {

    Event save(Event event);

    Optional<Event> findById(Long id);

    List<Event> findAll();

    List<Event> findByTitle(String title);

    List<Event> findByVenueId(Long venueId);

    List<Event> findByHostedBy(String hostedBy);

    boolean existsByTitle(String title);

    void deleteById(Long id);

    boolean existsById(Long id);
}
