package ru.newfirefly.restexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.newfirefly.restexample.model.Developer;
import ru.newfirefly.restexample.service.DeveloperService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    private final List<Developer> developerListlist = new ArrayList<>();

    {
        developerListlist.add(new Developer(1L, "q", "w"));
        developerListlist.add(new Developer((long) 2, "e", "r"));
        developerListlist.add(new Developer((long) 3, "t", "t"));
    }

    private final List<Developer> developerListFromSteam = Stream.of(
            new Developer(4L, "a", "s"),
            new Developer(5L, "d", "f"),
            new Developer(6L, "g", "h"))
            .collect(Collectors.toList());

    @GetMapping("/developers")
    public List<Developer> findAll() {
        return developerListFromSteam;
        //return developerService.findAll();
    }

    @GetMapping("developers/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return developerListlist.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        this.developerListFromSteam.add(developer);
        //developerService.save(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void delete(@PathVariable Long id) {
        developerService.delete(id);
    }


}
