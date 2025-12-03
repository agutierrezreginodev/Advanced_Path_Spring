package com.codeup.eventify.application.usecase.events;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.ports.in.events.DeleteEventUseCase;
import com.codeup.eventify.domain.ports.out.EventRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {
    private final EventRepositoryPort eventRepository;

    public DeleteEventUseCaseImpl(EventRepositoryPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleting event with ID: {}", id);

        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with ID: " + id);
        }

        eventRepository.deleteById(id);
        log.info("Event deleted successfully with ID: {}", id);
    }

}
