package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje,Long> {

    Set<Personaje> findAll(Specification<Personaje> spec);
}
