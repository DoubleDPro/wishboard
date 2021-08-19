package ru.usachev.wishboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.wishboard.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
