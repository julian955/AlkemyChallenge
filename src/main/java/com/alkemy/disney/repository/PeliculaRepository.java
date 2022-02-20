package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Pelicula;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    Set<Pelicula> findAll(Specification<Pelicula> spec);
}
