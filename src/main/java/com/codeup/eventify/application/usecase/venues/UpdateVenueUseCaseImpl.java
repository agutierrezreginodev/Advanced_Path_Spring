package com.codeup.eventify.application.usecase.venues;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.in.venues.UpdateVenueUseCase;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    @Override
    public Venue updateVenue(Long id, Venue venue) {
        return venueRepositoryPort.update(id, venue);
    }
}
