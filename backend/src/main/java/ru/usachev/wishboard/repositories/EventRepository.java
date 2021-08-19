package ru.usachev.wishboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.wishboard.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
