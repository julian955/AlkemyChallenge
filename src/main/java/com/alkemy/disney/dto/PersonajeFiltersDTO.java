package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeFiltersDTO {


    private String nombre;
    private Integer edad;
    private List<Long> idMovies;

    public PersonajeFiltersDTO(String nombre, Integer edad, List<Long> idMovies) {
        this.nombre = nombre;
        this.edad = edad;
        this.idMovies = idMovies;
    }
}
