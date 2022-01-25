package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonaBasicDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;
import com.alkemy.disney.disney.dto.PersonaFiltersDTO;
import com.alkemy.disney.disney.entity.PersonaEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PersonaMapper;
import com.alkemy.disney.disney.repository.PersonaRepository;
import com.alkemy.disney.disney.repository.specification.PersonaSpecification;
import com.alkemy.disney.disney.service.MovieOrTVSerieService;
import com.alkemy.disney.disney.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaMapper personaMapper;
    @Autowired
    PersonaRepository personaRepository;

    private MovieOrTVSerieService movieOrTVSerieService;
    private PersonaSpecification personaSpecification;

    @Autowired
    public PersonaServiceImpl(
            PersonaRepository personaRepository,
            PersonaSpecification personaSpecification,
            MovieOrTVSerieService movieOrTVSerieService,
            PersonaMapper personaMapper){
        this.personaRepository = personaRepository;
        this.personaSpecification = personaSpecification;
        this.personaMapper = personaMapper;
        this.movieOrTVSerieService = movieOrTVSerieService;
    }

    @Override
    public PersonaDTO save(PersonaDTO dto) {
        PersonaEntity entity = personaMapper.personaDTOToEntity(dto);
        PersonaEntity savedEntity = personaRepository.save(entity);
        PersonaDTO responseDTO = personaMapper.PersonaEntityToDTO(savedEntity,true);
        return responseDTO;
    }

    @Override
    public List<PersonaDTO> getAllPersonas() {
        List<PersonaEntity> entities = personaRepository.findAll();
        List<PersonaDTO> response = personaMapper.personaEntityListToDTOList(entities, true);
        return response;
    }


    public List<PersonaBasicDTO> getPersonasList() {
        List<PersonaEntity> entities = personaRepository.findAll();
        List<PersonaBasicDTO> response = personaMapper.personaEntityListToBasicDTOList(entities);
        return response;
    }

    @Override
    public void delete(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public PersonaDTO getDetailsByID(Long id) {
        Optional<PersonaEntity> entity = personaRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        PersonaDTO personaDTO = personaMapper.PersonaEntityToDTO(entity.get(), true);
        return personaDTO;
    }

    @Override
    public List<PersonaDTO> getByFilters(String name, Byte age, Short weight, List<Long> movies) {
        PersonaFiltersDTO filtersDTO = new PersonaFiltersDTO(name, age, weight, movies);
        List<PersonaEntity> entities = personaRepository.findAll(PersonaSpecification.getByFilters(filtersDTO));
        List<PersonaDTO> dtos = personaMapper.personaEntityListToDTOList(entities, true);
        return dtos;
    }

    @Override
    public PersonaDTO update(Long id, PersonaDTO persona) {
        Optional<PersonaEntity> entity = personaRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id no válido");
        }
        personaMapper.personaEntityRefreshValues(entity.get(), persona);
        PersonaEntity savedEntity = personaRepository.save(entity.get());
        PersonaDTO response = personaMapper.PersonaEntityToDTO(savedEntity,false);
        return response;
    }
}
