package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;
import com.alkemy.disney.disney.dto.PersonaBasicDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonaMapper {

    @Autowired
    @Lazy
    MovieOrTVSerieMapper movieOrTVSerieMapper;

    public PersonaDTO PersonaEntityToDTO(Persona entity, boolean loadMovies) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if(loadMovies){
            List<MovieOrTVSerieDTO> movieOrTVSerieDTOList = movieOrTVSerieMapper.movieOrTVSerieEntityListToDTOList(entity.getMovieOrTVSeries(), false);
            dto.setMovieOrTVSeries(movieOrTVSerieDTOList);
        }
        return dto;
    }

    public Persona personaDTOToEntity(PersonaDTO dto){
        Persona entity = new Persona();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        return entity;
    }

    public PersonaBasicDTO personaEntityToBasicDTO(Persona entity){
        PersonaBasicDTO dto = new PersonaBasicDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    public List<PersonaDTO> personaEntityListToDTOList(List<Persona> entities, boolean loadMovies){
        List<PersonaDTO> dtos= new ArrayList<>();
        for (Persona entity : entities){
            dtos.add(PersonaEntityToDTO(entity,loadMovies));
        }
        return dtos;
    }

    public Set<Persona> personaDTOListToEntityList(List<PersonaDTO> dtos) {
        Set<Persona> entities = new HashSet<>();
        for (PersonaDTO dto : dtos){
            entities.add(personaDTOToEntity(dto));
        }
        return entities;
    }

    public List<PersonaBasicDTO> personaEntityListToBasicDTOList(List<Persona> entities){
        List<PersonaBasicDTO> dtos = new ArrayList<>();
        for (Persona entity : entities){
            dtos.add(personaEntityToBasicDTO(entity));
        }
        return dtos;
    }

    public void personaEntityRefreshValues(Persona entity, PersonaDTO persona) {
        entity.setImage(persona.getImage());
        entity.setName(persona.getName());
        entity.setAge(persona.getAge());
        entity.setWeight(persona.getWeight());
        entity.setStory(persona.getStory());
    }
}
