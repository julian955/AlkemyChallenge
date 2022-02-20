package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.PeliculaFiltersDTO;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
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
public class PeliculaSpecification {


    public Specification<Pelicula> getByFilters(PeliculaFiltersDTO filtersDTO){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDTO.getNombre().toLowerCase() + "%"
                        )
                );

            }
            if (filtersDTO.getIdGenero() != null){
                predicates.add(
                        criteriaBuilder.equal(root.<Integer>get("generoId"),filtersDTO.getIdGenero())
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getIdPersonajes())){
                Join<Personaje, Pelicula> join = root.join("personajes" , JoinType.INNER);
                Expression<String> personajesId= join.get("id");
                predicates.add(personajesId.in(filtersDTO.getIdPersonajes()));

            }

            query.distinct(true);

/*
            String order = "order";

            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(order)) :
                            criteriaBuilder.desc(root.get(order))
            );
*/
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
