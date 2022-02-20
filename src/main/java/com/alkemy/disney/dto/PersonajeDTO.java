package com.alkemy.disney.dto;

import com.alkemy.disney.entity.Pelicula;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonajeDTO {

    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String historia ;

    private List<Long>  peliculasId;

    private List<Pelicula> peliculas = new ArrayList<>();


}
