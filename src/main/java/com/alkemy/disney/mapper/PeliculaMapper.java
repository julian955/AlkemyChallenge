package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.repository.GeneroRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component

public class PeliculaMapper {

    @Autowired
    PersonajeMapper personajeMapper;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    PersonajeRepository personajeRepository;

    public Pelicula dto2Entity(PeliculaDTO dto){
        Pelicula entity = new Pelicula();

        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setTitulo(dto.getTitulo());

        entity.setEstreno(
                string2LocalDate(dto.getEstreno())
        );
        entity.setGeneroId(dto.getGeneroId());

        Long genId = dto.getGeneroId();
        Optional<Genero> genero = generoRepository.findById(dto.getGeneroId());
        if (genero.isPresent()){
            entity.setGenero(genero.get());
        }

        if(dto.getPersonajes() != null){
            entity.setPersonajes(dto.getPersonajes());
        }

      if (dto.getPersonajesId() != null){
          List<Long> charId = dto.getPersonajesId();
          if (charId != null){
              Set<Personaje> charList = new HashSet<>();

              for (Long id: charId) {
                  Optional<Personaje> personaje = personajeRepository.findById(id);
                  if (personaje.isPresent()){
                      charList.add(personaje.get());
                  }

              }
              entity.setPersonajes(charList);
          }
      }



        return entity;
    }

    public PeliculaDTO peliEntity2DTO(Pelicula entity,boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setEstreno(entity.getEstreno().toString());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());

        if (loadPersonajes){
            /*Set<PersonajeDTO> listDTO = personajeMapper.persEntityList2DTOList(entity.getPersonajes(),false);
            dto.setPersonajesId(entity.getPersonajesId());*/
            dto.setPersonajes(entity.getPersonajes());
        }

        return dto;
    }

    public List<PeliculaDTO> peliEntityList2DTO(List<Pelicula> entityList, boolean loadPersonajes){

        List<PeliculaDTO> dtoList = new ArrayList<>();

        for (Pelicula entity: entityList) {
            dtoList.add(peliEntity2DTO(entity,loadPersonajes));
        }
        return dtoList;
    }

    public LocalDate string2LocalDate(String dateString){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString,dateTimeFormatter);
        return date;
    }

    public PeliculaBasicDTO peliEntity2BasicDTO (Pelicula entity , boolean loadPersonajes){

        PeliculaBasicDTO dto = new PeliculaBasicDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setEstreno(entity.getEstreno());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGenero(entity.getGenero().getNombre());

        if (loadPersonajes){

            List<String> personajes = new ArrayList<>();
            for (Personaje p : entity.getPersonajes()){
                personajes.add(p.getNombre());
            }
            dto.setPersonajes(personajes);
        }


        return dto;
    }

    public List<PeliculaBasicDTO> peliEntityList2PeliBasicDTOList(List<Pelicula> entityList, boolean loadPersonajes){

        List<PeliculaBasicDTO> dTOList = new ArrayList<>();

        for (Pelicula p : entityList){
            dTOList.add(peliEntity2BasicDTO(p,loadPersonajes));
        }
        return dTOList;
    }

    public Set<PeliculaBasicDTO> peliEntitySet2PeliBasicDTOSet(Set<Pelicula> entityList, boolean loadPersonajes){

        Set<PeliculaBasicDTO> dTOList = new HashSet<>();

        for (Pelicula p : entityList){
            dTOList.add(peliEntity2BasicDTO(p,loadPersonajes));
        }
        return dTOList;
    }
}
