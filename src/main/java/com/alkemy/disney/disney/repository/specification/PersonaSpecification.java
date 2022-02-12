package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.PersonaFiltersDTO;
import com.alkemy.disney.disney.entity.MovieOrTVSerie;
import com.alkemy.disney.disney.entity.Persona;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaSpecification {


    public static Specification<Persona> getByFilters(PersonaFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if (!filtersDTO.getAge().equals(null)){
                predicates.add(
                        criteriaBuilder.equal(root.<Byte>get("age"), filtersDTO.getAge())
                );
            }
            if (!filtersDTO.getWeight().equals(null)){
                predicates.add(
                        criteriaBuilder.equal(root.<Short>get("weight"), filtersDTO.getWeight())
                );
            }
            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<MovieOrTVSerie, Persona> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
