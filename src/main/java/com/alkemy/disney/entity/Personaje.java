package com.alkemy.disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia ;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();

    private Boolean alta = true;
}
