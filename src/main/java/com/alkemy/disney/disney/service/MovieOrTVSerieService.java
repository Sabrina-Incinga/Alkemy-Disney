package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;

import java.util.List;

public interface MovieOrTVSerieService {

    MovieOrTVSerieDTO save(MovieOrTVSerieDTO dto);

    List<MovieOrTVSerieDTO> getAllMovies();

    void delete(Long id);


    MovieOrTVSerieDTO getDetailsByID(Long id);

    List<MovieOrTVSerieDTO> getByFilters(String title, String genre);

    MovieOrTVSerieDTO update(Long id, MovieOrTVSerieDTO film);
}
