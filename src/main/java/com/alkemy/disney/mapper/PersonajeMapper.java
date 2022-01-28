package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        dto.setPeliculas(entity.getPeliculas());

        if (loadPeliculas){
            List<PeliculaDTO> peliculaDTOList = peliculaMapper.peliEntityList2DTO(entity.getPeliculas(),false);

        }

        return dto;
    }

    public List<PersonajeDTO> persEntityList2DTOList (Set<Personaje> EntityList, boolean loadPeliculas){

        List<PersonajeDTO> DTOList = new ArrayList<>();

        for (Personaje entity:EntityList) {
            DTOList.add(entity2DTO(entity,loadPeliculas));
        }
        return DTOList;

    }


}
