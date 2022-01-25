package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.MovieOrTVSerieEntity;
import com.alkemy.disney.disney.entity.PersonaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieOrTVSerieRepository extends JpaRepository<MovieOrTVSerieEntity, Long> {

    List<MovieOrTVSerieEntity> findAll(Specification<MovieOrTVSerieEntity> spec);
}
