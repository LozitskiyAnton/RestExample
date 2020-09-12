package ru.newfirefly.restexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newfirefly.restexample.model.User;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

public interface UserRepositiory extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
