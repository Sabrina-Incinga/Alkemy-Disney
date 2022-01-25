package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PersonaFiltersDTO {

    String name;
    Byte age;
    Short weight;
    List<Long> movies;

    public PersonaFiltersDTO(String name, Byte age, Short weight, List<Long> movies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
    }
}
