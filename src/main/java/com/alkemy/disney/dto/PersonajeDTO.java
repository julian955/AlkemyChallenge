package com.alkemy.disney.dto;

import com.alkemy.disney.entity.Pelicula;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PersonajeDTO {

    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia ;

    private Collection<Pelicula> peliculas = new ArrayList<>();


}
