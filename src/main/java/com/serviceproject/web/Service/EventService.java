package com.serviceproject.web.Service;

import com.serviceproject.web.dto.Eventdto;

import java.util.List;

public interface EventService {
    void createEvent(Long autosId , Eventdto eventdto);
    List<Eventdto> findAllEvents();

    Eventdto findByEventId(Long eventId);

    void updateEvent(Eventdto eventdto);

    void deleteEvent(long eventId);
}
