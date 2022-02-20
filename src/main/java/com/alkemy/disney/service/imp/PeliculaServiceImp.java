package com.alkemy.disney.service.imp;

import com.alkemy.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PeliculaFiltersDTO;
import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.repository.GeneroRepository;
import com.alkemy.disney.repository.PeliculaRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeliculaServiceImp implements PeliculaService {

    @Autowired
    PeliculaMapper peliculaMapper;
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    PeliculaSpecification peliculaSpecification;


    public PeliculaDTO save(PeliculaDTO dto){
        Pelicula entity = peliculaMapper.dto2Entity(dto);
        Long genId = dto.getGeneroId();
        Optional<Genero> genero = generoRepository.findById(dto.getGeneroId());
        if (genero.isPresent()){
            entity.setGenero(genero.get());
        }else {
            throw new ParamNotFound("Genero invalido");
        }

        List<Long> charId = dto.getPersonajesId();
        Set<Personaje> charList = new HashSet<>();

        for (Long id: charId) {
            Optional<Personaje> personaje = personajeRepository.findById(id);
            if (personaje.isPresent()){
                charList.add(personaje.get());
            }else {
                throw new ParamNotFound("id de personaje invalido");
            }

        }
        entity.setPersonajes(charList);

        Pelicula entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliEntity2DTO(entitySaved,false);
        System.out.println("GUARDAR PELICULA");
        return result;

    }

    public List<PeliculaBasicDTO> listPelicula(){

        List<Pelicula> entityList = peliculaRepository.findAll();
        List<PeliculaBasicDTO> dtoList = peliculaMapper.peliEntityList2PeliBasicDTOList(entityList,true);

        return dtoList;
    }

    public PeliculaDTO edit(PeliculaDTO dto,Long id){

        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        if (!pelicula.isPresent()){
           throw new ParamNotFound("pelicula no encontrada");

        }
        Pelicula peli = pelicula.get();
        System.out.println("PELICULA ENCONTRADA : "+peli.toString());
        PeliculaDTO peliDTO = peliculaMapper.peliEntity2DTO(peli,true);
        System.out.println("PELI TRANSFORMADA : "+peliDTO.toString());
        if (dto.getImagen() != null){
            peliDTO .setImagen(dto.getImagen());
        }
        if (dto.getTitulo() != null){
            peliDTO.setTitulo(dto.getTitulo());
        }
        if (dto.getEstreno() != null){
            peliDTO.setEstreno(dto.getEstreno());
        }
        if (dto.getCalificacion() != null){
            peliDTO.setCalificacion(dto.getCalificacion());
        }
        if (dto.getGeneroId() != null){
            peliDTO.setGeneroId(dto.getGeneroId());
        }
        if (dto.getPersonajesId() != null){
            peliDTO.setPersonajesId(dto.getPersonajesId());
        }
        if(!dto.getPersonajes().isEmpty()){
            peliDTO.setPersonajes(dto.getPersonajes());
        }
        System.out.println("PELI TRANSFORMADA PASANDO LOS METODOS : "+peliDTO.toString());

        Pelicula entity = peliculaMapper.dto2Entity(peliDTO);
        entity.setId(id);
        System.out.println("PELICULA FINAL : " + entity.toString());
        peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliEntity2DTO(entity,false);
        System.out.println("PELICULA FINAL DTO: " + result.toString());
        return result;

    }

    public void delete(Long id){ peliculaRepository.deleteById(id);}

   /* public void softDelete(Long id){

        Pelicula pelicula = peliculaRepository.getById(id);
        pelicula.setAlta(false);
        peliculaRepository.save(pelicula);

    }*/

    public Set<PeliculaBasicDTO> getByFilters(String nombre,Long idGenero,String order,List<Long> idPersonajes){
        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(nombre,idGenero,order,idPersonajes);
        Set<Pelicula> entities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filtersDTO));
        if (entities.isEmpty()){
            throw new ParamNotFound("pelicula no encontrada");
        }
        Set<PeliculaBasicDTO> dtoList = peliculaMapper.peliEntitySet2PeliBasicDTOSet(entities,true);

        return dtoList;

    }

}
