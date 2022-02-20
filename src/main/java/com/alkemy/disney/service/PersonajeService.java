package com.alkemy.disney.service;


import com.alkemy.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.dto.PersonajeDTO;


import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    Set<PersonajeBasicDTO> getByFilter (String nombre, Integer edad, List<Long> idMovies);

    List<PersonajeBasicDTO> getAll ();

    void delete(Long id);



    PersonajeDTO edit(PersonajeDTO dtoEdited, Long id);

}
