package com.alkemy.disney.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

  /*  @PostMapping
    public ResponseEntity<PersonajeDTO> save (@RequestBody PersonajeDTO personaje){

        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);

    }*/

}
