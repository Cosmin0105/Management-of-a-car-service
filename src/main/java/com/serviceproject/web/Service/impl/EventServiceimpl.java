package com.serviceproject.web.Service.impl;

import com.serviceproject.web.Service.EventService;
import com.serviceproject.web.dto.Eventdto;
import com.serviceproject.web.models.Autoturisme;
import com.serviceproject.web.models.Event;
import com.serviceproject.web.repository.AutoturismeRepository;
import com.serviceproject.web.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.serviceproject.web.mapper.EventMapper.mapToEvent;
import static com.serviceproject.web.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceimpl implements EventService {
    private EventRepository eventRepository;
    private AutoturismeRepository autoturismeRepository;

    @Autowired
    public EventServiceimpl(EventRepository eventRepository, AutoturismeRepository autoturismeRepository) {
        this.eventRepository = eventRepository;
        this.autoturismeRepository = autoturismeRepository;
    }

    @Override
    public void createEvent(Long autosId, Eventdto eventdto) {
        Autoturisme autoturisme = autoturismeRepository.findById(autosId).get();
        Event event = mapToEvent(eventdto);
        event.setAutoturisme(autoturisme);
        eventRepository.save(event);
    }

    @Override
    public List<Eventdto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public Eventdto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return  mapToEventDto(event);
    }

    @Override
    public void updateEvent(Eventdto eventdto) {
        Event event = mapToEvent(eventdto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }


}
