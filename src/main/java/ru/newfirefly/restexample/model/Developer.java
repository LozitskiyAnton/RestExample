package ru.newfirefly.restexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class Developer {

    private Long id;
    private String firsName;
    private String lastName;


}
