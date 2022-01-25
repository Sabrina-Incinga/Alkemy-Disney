package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MovieOrTVSerieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Byte rating;
    private GenreEntity genre;
    private List<PersonaDTO> personas;

}
