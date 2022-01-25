package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonaBasicDTO;
import com.alkemy.disney.disney.dto.PersonaDTO;

import java.util.List;

public interface PersonaService {

    PersonaDTO save(PersonaDTO dto);

    List<PersonaDTO> getAllPersonas();

    List<PersonaBasicDTO> getPersonasList();

    void delete(Long id);

    PersonaDTO getDetailsByID(Long id);

    List<PersonaDTO> getByFilters(String name, Byte age, Short weight, List<Long> movies);

    PersonaDTO update(Long id, PersonaDTO persona);
}
