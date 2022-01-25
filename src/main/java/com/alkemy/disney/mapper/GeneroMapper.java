package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entity.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public Genero generoDTO2Entity (GeneroDTO dto){
        Genero genero = new Genero();

        genero.setNombre(dto.getNombre());
        genero.setImagen(dto.getImagen());

        return genero;
    }

    public GeneroDTO generoEntity2DTO (Genero entity){
        GeneroDTO dto = new GeneroDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());

        return dto;
    }

}
