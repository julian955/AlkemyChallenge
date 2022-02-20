package com.alkemy.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET alta = false WHERE id =?")
@Where(clause = "alta = true")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_estreno")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate estreno;

    private int calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id" , insertable = false, updatable = false)
    private Genero genero;

    @Column(name = "genero_id", nullable = false )
    private Long generoId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")

    )
    private Set<Personaje> personajes = new HashSet<>();

   /* private List<Long> personajesId = new ArrayList<>();*/

    private boolean alta = true;

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", titulo='" + titulo + '\'' +
                ", estreno=" + estreno +
                ", calificacion=" + calificacion +
                ", genero=" + genero +
                ", generoId=" + generoId +
                ", personajes=" + personajes +
                ", alta=" + alta +
                '}';
    }
}
