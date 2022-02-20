package com.alkemy.disney.controller;


import com.alkemy.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> save (@RequestBody PeliculaDTO pelicula){

        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);

    }



    @GetMapping("/listar")
    public ResponseEntity<List<PeliculaBasicDTO>> listarPeliculas(){
        List<PeliculaBasicDTO> listarPeliculas = peliculaService.listPelicula();

        return ResponseEntity.status(HttpStatus.CREATED).body(listarPeliculas);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PeliculaDTO> edit (@PathVariable Long id,@RequestBody PeliculaDTO pelicula){

        PeliculaDTO peliculaEdit = peliculaService.edit(pelicula,id);
        System.out.println(pelicula.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaEdit);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping
    public ResponseEntity<Set<PeliculaBasicDTO>> findByFilter(
            @RequestParam (required = false) String nombre ,
            @RequestParam (required = false) Long idGenero ,
            @RequestParam (required = false) List<Long> idPersonajes,
            @RequestParam (required = false) String order

    ){
        Set<PeliculaBasicDTO> result = peliculaService.getByFilters(nombre, idGenero, order, idPersonajes);
        return ResponseEntity.ok(result);
    }






}
