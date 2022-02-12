package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Movie_TVSerie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE Movie_TVSerie set deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class MovieOrTVSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String image;
    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    private Byte rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private Long genreID;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "movie_TVserie_persona",
            joinColumns = @JoinColumn(name = "movie_TVserie_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id"))
    private Set<Persona> personas = new HashSet<>();

    private boolean deleted = Boolean.FALSE;


}
