package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeliculaFiltersDTO {


    private String nombre;
    private Long idGenero;
    private String order;
    private List<Long> idPersonajes;

    public PeliculaFiltersDTO(String nombre, Long idGenero, String order,List<Long> idPersonajes) {
        this.nombre = nombre;
        this.idGenero = idGenero;
        this.order = order;
        this.idPersonajes = idPersonajes;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC") == 0;}
}
