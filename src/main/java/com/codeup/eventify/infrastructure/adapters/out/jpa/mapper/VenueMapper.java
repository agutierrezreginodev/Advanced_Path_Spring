package com.codeup.eventify.infrastructure.adapters.out.jpa.mapper;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.VenueEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    Venue toDomain(VenueEntity venueEntity);

    VenueEntity toEntity(Venue venue);
}
