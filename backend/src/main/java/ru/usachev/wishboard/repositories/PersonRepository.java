package ru.usachev.wishboard.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.wishboard.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

  Optional<Person> findByEmail(String email);
}
