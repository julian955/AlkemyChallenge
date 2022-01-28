package com.alkemy.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET alta = false WHERE id =?")
@Where(clause = "alta = true")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

    private boolean alta = true;
}
