package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.disney.entity.MovieOrTVSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FilmSpecification {
    public static Specification<MovieOrTVSerieEntity> getByFilters(FilmFiltersDTO filtersDTO) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDTO.getGenre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("genre")),
                                "%" + filtersDTO.getGenre().toLowerCase() + "%"
                        )
                );
            }
            query.distinct(true);


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
