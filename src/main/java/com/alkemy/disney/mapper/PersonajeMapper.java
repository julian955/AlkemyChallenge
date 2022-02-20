package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Lazy
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public Personaje dto2Entity (PersonajeDTO dto){

        Personaje entity = new Personaje();
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());

        return entity;
    }

    public PersonajeDTO entity2DTO (Personaje entity, boolean loadPeliculas){

        PersonajeDTO dto = new PersonajeDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());


        if (loadPeliculas){
            dto.setPeliculas(entity.getPeliculas());
        }

        return dto;
    }

    public Set<PersonajeDTO> persEntityList2DTOList (Set<Personaje> EntityList, boolean loadPeliculas){

        Set<PersonajeDTO> DTOList = new HashSet<>();

        for (Personaje entity:EntityList) {
            DTOList.add(entity2DTO(entity,loadPeliculas));
        }
        return DTOList;

    }


    public PersonajeBasicDTO persEntity2BasicDTO(Personaje entity, boolean loadPeliculas){

        PersonajeBasicDTO dto = new PersonajeBasicDTO();


        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setHistoria(entity.getHistoria());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());

       if (loadPeliculas){
           List<String> peliculas = new ArrayList<>();
           for (Pelicula nombre : entity.getPeliculas()){
               peliculas.add(nombre.getTitulo());
           }
           dto.setPeliculas(peliculas);
       }

        return dto;
    }

    public Set<PersonajeBasicDTO> persEntitySet2BasicDTOSet (Set<Personaje> EntityList, boolean loadPeliculas){

        Set<PersonajeBasicDTO> DTOList = new HashSet<>();

        for (Personaje entity:EntityList) {
            DTOList.add(persEntity2BasicDTO(entity,loadPeliculas));
        }
        return DTOList;

    }

    public List<PersonajeBasicDTO> persEntityList2BasicDTOList (List<Personaje> EntityList, boolean loadPeliculas){

        List<PersonajeBasicDTO> DTOList = new ArrayList<>();

        for (Personaje entity:EntityList) {
            DTOList.add(persEntity2BasicDTO(entity,loadPeliculas));
        }
        return DTOList;

    }

    public Personaje persBasicDTO2Entity (PersonajeBasicDTO dto){

        Personaje entity = new Personaje();

        entity.setId(dto.getId());
        entity.setHistoria(dto.getHistoria());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());

        return entity;
    }


}
