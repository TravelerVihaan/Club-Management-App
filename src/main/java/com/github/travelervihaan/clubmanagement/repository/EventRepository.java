package com.github.travelervihaan.clubmanagement.repository;

import com.github.travelervihaan.clubmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
