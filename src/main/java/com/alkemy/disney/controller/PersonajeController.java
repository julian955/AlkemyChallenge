package com.alkemy.disney.controller;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeGuardado = personajeService.save(personaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @GetMapping("/listar")
    public ResponseEntity<List<PersonajeBasicDTO>> getAll(){
        List<PersonajeBasicDTO> dtoList = personajeService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoList);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PersonajeDTO> edit (@PathVariable Long id, @RequestBody PersonajeDTO personaje){

        PersonajeDTO peliculaEdit = personajeService.edit(personaje,id);

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaEdit);
    }


    @GetMapping
    public ResponseEntity<Set<PersonajeBasicDTO>> findByFilter(
            @RequestParam (required = false) String nombre ,
            @RequestParam (required = false) Integer edad ,
            @RequestParam (required = false) List<Long> peliculaId

    ){
        Set<PersonajeBasicDTO> result = personajeService.getByFilter(nombre, edad, peliculaId);
        return ResponseEntity.ok(result);
    }



}
