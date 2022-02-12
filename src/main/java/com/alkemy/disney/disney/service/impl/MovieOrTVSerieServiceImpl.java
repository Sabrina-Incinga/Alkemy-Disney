package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;
import com.alkemy.disney.disney.entity.MovieOrTVSerie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieOrTVSerieMapper;
import com.alkemy.disney.disney.repository.MovieOrTVSerieRepository;
import com.alkemy.disney.disney.repository.specification.FilmSpecification;
import com.alkemy.disney.disney.service.MovieOrTVSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieOrTVSerieServiceImpl implements MovieOrTVSerieService {

    @Autowired
    private MovieOrTVSerieMapper movieOrTVSerieMapper;
    @Autowired
    private MovieOrTVSerieRepository movieOrTVSerieRepository;


    @Override
    public MovieOrTVSerieDTO save(MovieOrTVSerieDTO dto) {
        MovieOrTVSerie entity = movieOrTVSerieMapper.movieOrTVSerieDTOToEntity(dto);
        MovieOrTVSerie savedEntity = movieOrTVSerieRepository.save(entity);
        MovieOrTVSerieDTO responseDTO = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(savedEntity, true);
        return responseDTO;
    }

    @Override
    public List<MovieOrTVSerieDTO> getAllMovies() {
        List<MovieOrTVSerie> entities = movieOrTVSerieRepository.findAll();
        List<MovieOrTVSerieDTO> response = movieOrTVSerieMapper.movieOrTVSerieEntityListToDTOList(entities, true);
        return response;
    }

    @Override
    public void delete(Long id) {
        movieOrTVSerieRepository.deleteById(id);
    }

    @Override
    public MovieOrTVSerieDTO getDetailsByID(Long id) {
        Optional<MovieOrTVSerie> entity = movieOrTVSerieRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        MovieOrTVSerieDTO dto = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(entity.get(), true);
        return dto;
    }

    @Override
    public List<MovieOrTVSerieDTO> getByFilters(String title, String genre) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(title, genre);
        List<MovieOrTVSerie> entities = movieOrTVSerieRepository.findAll(FilmSpecification.getByFilters(filtersDTO));
        List<MovieOrTVSerieDTO> dtos = movieOrTVSerieMapper.movieOrTVSerieEntityListToDTOList(entities, true);
        return dtos;
    }

    @Override
    public MovieOrTVSerieDTO update(Long id, MovieOrTVSerieDTO film) {
        Optional<MovieOrTVSerie> entity = movieOrTVSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        movieOrTVSerieMapper.filmEntityRefreshValues(entity.get(), film);
        MovieOrTVSerie savedEntity = movieOrTVSerieRepository.save(entity.get());
        MovieOrTVSerieDTO response = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(savedEntity,false);
        return response;    }
}
