package ru.newfirefly.restexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.newfirefly.restexample.model.Developer;
import ru.newfirefly.restexample.repository.DeveloperRepository;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer findById(Long id){
       return developerRepository.getOne(id);
    }

    public List<Developer> findAll(){
       return developerRepository.findAll();
    }

    public Developer save(Developer developer){
        developerRepository.save(developer);

        return developer;
    }

    public void delete(Long id){
        developerRepository.deleteById(id);
    }
}
