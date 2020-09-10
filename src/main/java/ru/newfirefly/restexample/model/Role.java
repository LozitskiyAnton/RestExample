package ru.newfirefly.restexample.model;




import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum Role {

    USER(Collections.singleton(Permission.DEVELOPERS_READ)),
    ADMIN(Stream.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE).collect(Collectors.toSet()));




    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions=permissions;
    }
}
