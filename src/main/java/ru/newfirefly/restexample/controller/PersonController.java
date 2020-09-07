package ru.newfirefly.restexample.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.newfirefly.restexample.model.Person;
import ru.newfirefly.restexample.repository.PersonRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("json")
    public void json(){
        URL url = this.getClass().getClassLoader().getResource("people.json");
        if(url != null){
            File jsonFile= new File(url.getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Person> list = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {
                });
                personRepository.saveAll(list);
                logger.info("OK");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.warn("not OK");
        }
    }

}
