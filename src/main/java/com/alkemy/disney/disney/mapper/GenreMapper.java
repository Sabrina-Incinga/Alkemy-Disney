package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTOToEntity(GenreDTO dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());

        return genreEntity;
    }

    public GenreDTO genreEntityToDTO(GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());

        return dto;
    }

    public List<GenreDTO> genreEntityListToDTOList(List<GenreEntity> entities){
        List<GenreDTO> dtos = new ArrayList<>();
        for(GenreEntity entity : entities){
            dtos.add(genreEntityToDTO(entity));
        }
        return dtos;
    }
}
