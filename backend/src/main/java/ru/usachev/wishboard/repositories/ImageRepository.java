package ru.usachev.wishboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.wishboard.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
