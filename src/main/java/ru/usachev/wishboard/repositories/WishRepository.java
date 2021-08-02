package ru.usachev.wishboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.wishboard.entities.Wish;

public interface WishRepository extends JpaRepository<Wish, Integer> {

}
