package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonaDTO {

    private Long id;
    private String image;
    private String name;
    private Byte age;
    private Short weight;
    private String story;
    private List<MovieOrTVSerieDTO> movieOrTVSeries;

}
