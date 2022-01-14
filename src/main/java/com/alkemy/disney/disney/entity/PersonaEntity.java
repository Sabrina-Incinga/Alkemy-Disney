package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter

public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;
    private Byte age;
    private Short weight;
    private String story;

    @ManyToMany(mappedBy = "personas", cascade = CascadeType.ALL)
    private List<MovieOrTVSerieEntity> movieOrTVSeries = new ArrayList<>();

}
