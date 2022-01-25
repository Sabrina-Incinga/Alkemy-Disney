package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmFiltersDTO {

    String title;
    String genre;

    public FilmFiltersDTO(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
}
