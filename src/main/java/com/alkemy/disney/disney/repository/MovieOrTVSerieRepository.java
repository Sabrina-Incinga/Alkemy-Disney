package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.MovieOrTVSerie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieOrTVSerieRepository extends JpaRepository<MovieOrTVSerie, Long> {

    List<MovieOrTVSerie> findAll(Specification<MovieOrTVSerie> spec);
}
