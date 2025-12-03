package com.codeup.eventify.application.usecase.venues;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.venues.RetrieveVenueUseCase;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RetrieveVenueUseCaseImpl implements RetrieveVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public RetrieveVenueUseCaseImpl(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue getVenueById(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with ID: " + id);
        }
        log.info("Fetching venue with ID: {}", id);

        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + id));
    }

    @Override
    public List<Venue> getAllVenues() {
        log.info("Fetching all venues");
        return venueRepository.findAll();
    }
}
