package com.codeup.eventify.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.codeup.eventify.domain.model.Venue;

public interface VenueRepositoryPort {

    Venue save(Venue venue);

    Optional<Venue> findById(Long id);

    List<Venue> findAll();

    Optional<Venue> findByName(String name);

    boolean existsByName(String name);

    void deleteById(Long id);

    boolean existsById(Long id);

    Venue update(Long id, Venue venue);
}
