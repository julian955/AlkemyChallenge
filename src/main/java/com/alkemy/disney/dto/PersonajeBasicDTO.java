package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeBasicDTO {

    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia ;

    private List<String> peliculas;
}
