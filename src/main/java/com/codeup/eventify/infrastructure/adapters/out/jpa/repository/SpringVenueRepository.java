package com.codeup.eventify.infrastructure.adapters.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.VenueEntity;

import java.util.Optional;

@Repository
public interface SpringVenueRepository extends JpaRepository<VenueEntity, Long> {
    Optional<VenueEntity> findByName(String name);

    boolean existsByName(String name);
}
