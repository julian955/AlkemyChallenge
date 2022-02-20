package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class PeliculaBasicDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private LocalDate estreno;

    private Integer calificacion;

    private String genero;

    private List<String> personajes;
}
