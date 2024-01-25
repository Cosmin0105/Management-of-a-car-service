package com.serviceproject.web.mapper;

import com.serviceproject.web.dto.Autoturismedto;
import com.serviceproject.web.models.Autoturisme;

import java.util.stream.Collectors;

import static com.serviceproject.web.mapper.EventMapper.mapToEvent;
import static com.serviceproject.web.mapper.EventMapper.mapToEventDto;

public class AutoturismeMapper {
    public static Autoturisme mapToAutoturisme(Autoturismedto auto) {
        Autoturisme autoturismedto3 = Autoturisme.builder()
                .id(auto.getId())
                .marca((auto.getMarca()))
                .photoUrl(auto.getPhotoUrl())
                .descriere(auto.getDescriere())
                .createdBy(auto.getCreatedBy())
                .createOn(auto.getCreateOn())
                .updateOn(auto.getUpdateOn())
                .build();
        return  autoturismedto3;
    }


    public static Autoturismedto mapToAutoturismeDto(Autoturisme autoturisme){
        Autoturismedto autoturismedto2 = Autoturismedto.builder()
                .id(autoturisme.getId())
                .marca(autoturisme.getMarca())
                .photoUrl(autoturisme.getPhotoUrl())
                .descriere(autoturisme.getDescriere())
                .createdBy(autoturisme.getCreatedBy())
                .createOn(autoturisme.getCreateOn())
                .updateOn(autoturisme.getUpdateOn())
                .events(autoturisme.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return  autoturismedto2;
    }

}
