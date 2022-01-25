package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.disney.dto.MovieOrTVSerieDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.dto.PersonaFiltersDTO;
import com.alkemy.disney.disney.entity.MovieOrTVSerieEntity;
import com.alkemy.disney.disney.entity.PersonaEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieOrTVSerieMapper;
import com.alkemy.disney.disney.repository.MovieOrTVSerieRepository;
import com.alkemy.disney.disney.repository.specification.FilmSpecification;
import com.alkemy.disney.disney.repository.specification.PersonaSpecification;
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
        MovieOrTVSerieEntity entity = movieOrTVSerieMapper.movieOrTVSerieDTOToEntity(dto);
        MovieOrTVSerieEntity savedEntity = movieOrTVSerieRepository.save(entity);
        MovieOrTVSerieDTO responseDTO = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(savedEntity, true);
        return responseDTO;
    }

    @Override
    public List<MovieOrTVSerieDTO> getAllMovies() {
        List<MovieOrTVSerieEntity> entities = movieOrTVSerieRepository.findAll();
        List<MovieOrTVSerieDTO> response = movieOrTVSerieMapper.movieOrTVSerieEntityListToDTOList(entities, true);
        return response;
    }

    @Override
    public void delete(Long id) {
        movieOrTVSerieRepository.deleteById(id);
    }

    @Override
    public MovieOrTVSerieDTO getDetailsByID(Long id) {
        Optional<MovieOrTVSerieEntity> entity = movieOrTVSerieRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        MovieOrTVSerieDTO dto = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(entity.get(), true);
        return dto;
    }

    @Override
    public List<MovieOrTVSerieDTO> getByFilters(String title, String genre) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(title, genre);
        List<MovieOrTVSerieEntity> entities = movieOrTVSerieRepository.findAll(FilmSpecification.getByFilters(filtersDTO));
        List<MovieOrTVSerieDTO> dtos = movieOrTVSerieMapper.movieOrTVSerieEntityListToDTOList(entities, true);
        return dtos;
    }

    @Override
    public MovieOrTVSerieDTO update(Long id, MovieOrTVSerieDTO film) {
        Optional<MovieOrTVSerieEntity> entity = movieOrTVSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        movieOrTVSerieMapper.filmEntityRefreshValues(entity.get(), film);
        MovieOrTVSerieEntity savedEntity = movieOrTVSerieRepository.save(entity.get());
        MovieOrTVSerieDTO response = movieOrTVSerieMapper.movieOrTVSerieEntityToDTO(savedEntity,false);
        return response;    }
}
