package com.alkemy.disney.repository.specifications;


import com.alkemy.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class PersonajeSpecification {
    PersonajeFiltersDTO filtersDTO;



    public Specification<Personaje> getByFilters(PersonajeFiltersDTO filtersDTO){
        return(root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDTO.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getNombre().toLowerCase() + "%"
                        )
                );

            }

            if (filtersDTO.getEdad() != null){
                predicates.add(
                        criteriaBuilder.equal(root.<Integer>get("edad"),filtersDTO.getEdad())
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getIdMovies())){
                Join<Pelicula, Personaje> join = root.join("peliculas" , JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filtersDTO.getIdMovies()));
            }

            query.distinct(true);

            /*
            String order = "denominacion";

            query.orderBy(
                    filtersDTO.
            )*/

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
