package ru.newfirefly.restexample.model;




import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
