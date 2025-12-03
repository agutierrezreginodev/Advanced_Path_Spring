package com.codeup.eventify.application.usecase.venues;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.venues.CreateVenueUseCase;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {
    private final VenueRepositoryPort venueRepository;

    public CreateVenueUseCaseImpl(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue createVenue(Venue venue) {
        if (venueRepository.existsByName(venue.getName())) {
            throw new RuntimeException("Venue already exists with name: " + venue.getName());
        }
        if (venue.getName() == null || venue.getName().isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be null or empty");
        }
        if (venue.getCountry() == null || venue.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Venue country cannot be null or empty");
        }
        if (venue.getAddress() == null || venue.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Venue address cannot be null or empty");
        }
        if (venue.getCity() == null || venue.getCity().isEmpty()) {
            throw new IllegalArgumentException("Venue city cannot be null or empty");
        }
        if (venue.getState() == null || venue.getState().isEmpty()) {
            throw new IllegalArgumentException("Venue state cannot be null or empty");
        }
        if (venue.getZipCode() == null || venue.getZipCode().isEmpty()) {
            throw new IllegalArgumentException("Venue zip code cannot be null or empty");
        }
        log.info("Creating venue with name: {}", venue.getName());

        Venue createdVenue = Venue.builder()
                .name(venue.getName())
                .country(venue.getCountry())
                .address(venue.getAddress())
                .city(venue.getCity())
                .state(venue.getState())
                .zipCode(venue.getZipCode())
                .build();

        Venue savedVenue = venueRepository.save(createdVenue);
        log.info("Venue created successfully with ID: {}", savedVenue.getId());
        return savedVenue;
    }
}
