package ru.newfirefly.restexample.repository;

import org.springframework.data.repository.CrudRepository;
import ru.newfirefly.restexample.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
