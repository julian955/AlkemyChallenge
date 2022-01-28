package com.alkemy.disney.dto;

import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private String estreno;

    private int calificacion;

    private Long generoId;

    private Collection<Personaje> personajes;


}
