package ru.newfirefly.restexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newfirefly.restexample.model.Developer;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    List<Developer> findByLastName(String lastName);
}