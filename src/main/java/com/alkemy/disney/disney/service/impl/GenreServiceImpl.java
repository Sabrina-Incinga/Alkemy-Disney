package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto){
        Genre entity = genreMapper.genreDTOToEntity(dto);
        Genre savedEntity = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntityToDTO(savedEntity);
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> entities = genreRepository.findAll();
        List<GenreDTO> response = genreMapper.genreEntityListToDTOList(entities);
        return response;
    }

    @Override
    public void delete(Long id) {
        this.genreRepository.deleteById(id);
    }
}
