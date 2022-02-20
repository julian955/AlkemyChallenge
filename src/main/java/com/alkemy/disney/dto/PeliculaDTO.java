package com.alkemy.disney.dto;

import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private String estreno;

    private Integer calificacion;

    private Long generoId;

    private List<Long>  personajesId;

    private Set<Personaje> personajes = new HashSet<>();

    @Override
    public String toString() {
        return "PeliculaDTO{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", titulo='" + titulo + '\'' +
                ", estreno='" + estreno + '\'' +
                ", calificacion=" + calificacion +
                ", generoId=" + generoId +
                ", personajesId=" + personajesId +
                ", personajes=" + personajes +
                '}';
    }
}
