package com.alkemy.disney.service;

import com.alkemy.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.dto.PeliculaDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);

    List<PeliculaBasicDTO> listPelicula();

    PeliculaDTO edit(PeliculaDTO dto,Long id);

    void delete(Long id);



    Set<PeliculaBasicDTO> getByFilters(String nombre, Long idGenero, String order, List<Long> idPersonajes);
}
