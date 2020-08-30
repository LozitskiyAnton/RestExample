package ru.newfirefly.restexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
            new Developer((long) 4, "a", "s"),
            new Developer((long) 5, "d", "f"),
            new Developer((long) 4, "g", "h"))
            .collect(Collectors.toList());

    @GetMapping("/developers")
    public List<Developer> findAll(){
        return developerService.findAll();
    }

    @GetMapping("developers/{id}")
    public Developer getById(@PathVariable Long id){
        return developerListlist.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/create")
    public Developer create(@RequestBody Developer developer){
        //this.developerListlist.add(developer);
        //developerService.save(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        developerService.delete(id);
    }



}
