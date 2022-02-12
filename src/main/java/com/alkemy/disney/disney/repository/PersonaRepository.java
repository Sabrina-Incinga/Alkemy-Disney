package com.alkemy.disney.disney.repository;


import com.alkemy.disney.disney.entity.Persona;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findAll(Specification<Persona> spec);
}
