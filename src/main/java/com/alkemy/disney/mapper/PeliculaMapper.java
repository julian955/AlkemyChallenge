package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component

public class PeliculaMapper {

    @Autowired
    PersonajeMapper personajeMapper;

    public Pelicula dto2Entity(PeliculaDTO dto){
        Pelicula entity = new Pelicula();

        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setTitulo(dto.getTitulo());

        entity.setEstreno(
                string2LocalDate(dto.getEstreno())
        );
        entity.setGeneroId(dto.getGeneroId());

        return entity;
    }

    public PeliculaDTO peliEntity2DTO(Pelicula entity,boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setEstreno(entity.getEstreno().toString());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());

        if (loadPersonajes){
            List<PersonajeDTO> listDTO = personajeMapper.persEntityList2DTOList(entity.getPersonajes(),false);
        }

        return dto;
    }

    public List<PeliculaDTO> peliEntityList2DTO(List<Pelicula> entityList, boolean loadPersonajes){

        List<PeliculaDTO> dtoList = new ArrayList<>();

        for (Pelicula entity: entityList) {
            dtoList.add(peliEntity2DTO(entity,loadPersonajes));
        }
        return dtoList;
    }

    public LocalDate string2LocalDate(String dateString){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString,dateTimeFormatter);
        return date;
    }
}
