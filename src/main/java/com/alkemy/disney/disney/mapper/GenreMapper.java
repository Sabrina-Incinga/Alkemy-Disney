package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public Genre genreDTOToEntity(GenreDTO dto){
        Genre genre = new Genre();
        genre.setImage(dto.getImage());
        genre.setName(dto.getName());

        return genre;
    }

    public GenreDTO genreEntityToDTO(Genre entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());

        return dto;
    }

    public List<GenreDTO> genreEntityListToDTOList(List<Genre> entities){
        List<GenreDTO> dtos = new ArrayList<>();
        for(Genre entity : entities){
            dtos.add(genreEntityToDTO(entity));
        }
        return dtos;
    }
}
