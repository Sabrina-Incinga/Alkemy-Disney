package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.entity.MovieOrTVSerie;
import com.alkemy.disney.disney.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MovieOrTVSerieMapper {

    @Autowired
    PersonaMapper personaMapper;

    public MovieOrTVSerieDTO movieOrTVSerieEntityToDTO(MovieOrTVSerie entity, boolean loadPersonas){
        MovieOrTVSerieDTO dto = new MovieOrTVSerieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        dto.setGenre(entity.getGenre());
        if(loadPersonas){
            List<PersonaDTO> personaDTOS = this.personaMapper.personaEntityListToDTOList((List<Persona>) entity.getPersonas(), false);
            dto.setPersonas(personaDTOS);
        }

        return dto;
    }

    public MovieOrTVSerie movieOrTVSerieDTOToEntity(MovieOrTVSerieDTO dto){
        MovieOrTVSerie entity = new MovieOrTVSerie();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(stringToLocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        entity.setGenre(dto.getGenre());
        Set<Persona> personas = this.personaMapper.personaDTOListToEntityList(dto.getPersonas());
        entity.setPersonas(personas);
        return entity;
    }

    public List<MovieOrTVSerieDTO> movieOrTVSerieEntityListToDTOList(List<MovieOrTVSerie> entities, boolean loadPersonas) {
        List<MovieOrTVSerieDTO> movieOrTVSerieDTOList = new ArrayList<>();
        for (MovieOrTVSerie entity : entities){
            movieOrTVSerieDTOList.add(movieOrTVSerieEntityToDTO(entity, loadPersonas));
        }
        return movieOrTVSerieDTOList;
    }

    public LocalDate stringToLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }


    public void filmEntityRefreshValues(MovieOrTVSerie entity, MovieOrTVSerieDTO film) {
        entity.setImage(film.getImage());
        entity.setGenre(film.getGenre());
        entity.setTitle(film.getTitle());
        entity.setCreationDate(stringToLocalDate(film.getCreationDate()));
        entity.setRating(film.getRating());
    }
}
