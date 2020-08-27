package ru.newfirefly.restexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.newfirefly.restexample.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class DeveloperController {

    private final List<Developer> developerListlist = new ArrayList<>();

    {
        developerListlist.add(new Developer((long) 1, "q", "w"));
        developerListlist.add(new Developer((long) 2, "e", "r"));
        developerListlist.add(new Developer((long) 3, "t", "t"));
    }

    private final List<Developer> developerListFromSteam = Stream.of(
            new Developer((long) 4, "a", "s"),
            new Developer((long) 5, "d", "f"),
            new Developer((long) 4, "g", "h"))
            .collect(Collectors.toList());

    @GetMapping("/developers")
    public List<Developer> findAll(){
        return developerListFromSteam;
    }

}
