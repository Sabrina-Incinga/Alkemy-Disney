package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
@SQLDelete(sql = "UPDATE persona set deleted = true WHERE id=?")
@Where(clause = "deleted=false")


public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Byte age;
    private Short weight;
    private String story;

    @ManyToMany(mappedBy = "personas", cascade = CascadeType.MERGE)
    private List<MovieOrTVSerieEntity> movieOrTVSeries = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;
    

}
