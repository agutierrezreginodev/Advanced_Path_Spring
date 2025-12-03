package com.codeup.eventify.infrastructure.adapters.out.jpa.mapper;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.VenueEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "venue", target = "venue", qualifiedByName = "venueEntityToVenue")
    Event toDomain(EventEntity eventEntity);

    @Mapping(source = "venue", target = "venue", qualifiedByName = "venueToVenueEntity")
    EventEntity toEntity(Event event);

    @Named("venueEntityToVenue")
    default Venue venueEntityToVenue(VenueEntity venueEntity) {
        if (venueEntity == null) {
            return null;
        }
        return Venue.builder()
                .id(venueEntity.getId())
                .name(venueEntity.getName())
                .country(venueEntity.getCountry())
                .address(venueEntity.getAddress())
                .city(venueEntity.getCity())
                .state(venueEntity.getState())
                .zipCode(venueEntity.getZipCode())
                .build();
    }

    @Named("venueToVenueEntity")
    default VenueEntity venueToVenueEntity(Venue venue) {
        if (venue == null) {
            return null;
        }
        return VenueEntity.builder()
                .id(venue.getId())
                .name(venue.getName())
                .country(venue.getCountry())
                .address(venue.getAddress())
                .city(venue.getCity())
                .state(venue.getState())
                .zipCode(venue.getZipCode())
                .build();
    }
}
