package com.serviceproject.web.mapper;

import com.serviceproject.web.dto.Eventdto;
import com.serviceproject.web.models.Event;

public class EventMapper {

    public static Event mapToEvent(Eventdto eventdto)
    {
        return Event.builder()
                .id(eventdto.getId())
                .name(eventdto.getName())
                .startTime(eventdto.getStartTime())
                .endTime(eventdto.getEndTime())
                .type(eventdto.getType())
                .photoUrl(eventdto.getPhotoUrl())
                .createdOn(eventdto.getCreatedOn())
                .updatedOn(eventdto.getUpdatedOn())
                .autoturisme(eventdto.getAuturisme())
                .build();
    }
    public static Eventdto mapToEventDto(Event event)
    {
        return Eventdto.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .auturisme(event.getAutoturisme())
                .build();
    }
}
