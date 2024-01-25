package com.serviceproject.web.repository;

import com.serviceproject.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long > {
}
