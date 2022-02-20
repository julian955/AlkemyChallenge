package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> save (@RequestBody GeneroDTO genero){

        GeneroDTO generoGuardado = generoService.save(genero);

        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);

    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarGeneros(){
        List<GeneroDTO> listaGeneros = generoService.returnList();

        return ResponseEntity.status(HttpStatus.CREATED).body(listaGeneros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        generoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
