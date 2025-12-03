package com.codeup.eventify.application.usecase.venues;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.ports.in.venues.DeleteVenueUseCase;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {
    private final VenueRepositoryPort venueRepository;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with ID: " + id);
        }
        log.info("Deleting venue with ID: {}", id);

        venueRepository.deleteById(id);
        log.info("Venue deleted successfully with ID: {}", id);
    }
}
