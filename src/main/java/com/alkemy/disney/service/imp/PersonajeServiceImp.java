package com.alkemy.disney.service.imp;

import com.alkemy.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repository.PeliculaRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.specifications.PersonajeSpecification;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImp implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PersonajeSpecification personajeSpecification;
    @Autowired
    private PeliculaRepository peliculaRepository;

    public PersonajeDTO save(PersonajeDTO dto){

       Personaje entity = personajeMapper.dto2Entity(dto);
       Personaje entitySaved = personajeRepository.save(entity);
       PersonajeDTO result = personajeMapper.entity2DTO(entitySaved,false);
        System.out.println("GUARDAR GENERO");
        return result;

    }

    public Set<PersonajeBasicDTO> getByFilter (String nombre,Integer edad,List<Long> idMovies){
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(nombre,edad,idMovies);
        Set<Personaje> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filtersDTO));
        if (entities.isEmpty()){
            throw new ParamNotFound("personaje no encontrado");
        }
        Set<PersonajeBasicDTO> dtos = personajeMapper.persEntitySet2BasicDTOSet(entities,true);
        return dtos;
    }



    public List<PersonajeBasicDTO> getAll (){
        List<Personaje> entityList = personajeRepository.findAll();
        List<PersonajeBasicDTO> dtoList = personajeMapper.persEntityList2BasicDTOList(entityList,true);
        return dtoList;
    }

    public void delete(Long id){personajeRepository.deleteById(id);}

  /*  public void softDelete(Long id){

        Personaje personaje = personajeRepository.getById(id);
        personaje.setAlta(false);
        personajeRepository.save(personaje);

    }*/

    public PersonajeDTO edit(PersonajeDTO dtoEdited, Long id){

        Optional<Personaje> optionalPersonaje = personajeRepository.findById(id);

        if (!optionalPersonaje.isPresent()){
            throw new ParamNotFound("ID Invalido");
        }

        Personaje entity = optionalPersonaje.get();

        if (dtoEdited.getImagen() != null){
            entity.setImagen(dtoEdited.getImagen());
        }

        if (dtoEdited.getNombre() != null) {
            entity.setNombre(dtoEdited.getNombre());
        }

        if (dtoEdited.getEdad() != null) {
            entity.setEdad(dtoEdited.getEdad());
        }

        if (dtoEdited.getPeso() != null) {
            entity.setPeso(dtoEdited.getPeso());
        }

        if (dtoEdited.getHistoria() != null) {
            entity.setHistoria(dtoEdited.getHistoria());
        }

        if (dtoEdited.getPeliculasId() != null){
            List<Pelicula> peliculaList = new ArrayList<>();
            for (Long id1: dtoEdited.getPeliculasId()) {
                peliculaList.add(peliculaRepository.getById(id1));
            }

            entity.setPeliculas(peliculaList);
        }

        personajeRepository.save(entity);

        PersonajeDTO dto = personajeMapper.entity2DTO(entity,false);
        return dto;
    }
}
