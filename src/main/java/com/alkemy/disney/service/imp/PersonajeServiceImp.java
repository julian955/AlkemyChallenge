package com.alkemy.disney.service.imp;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImp implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;

    public PersonajeDTO save(PersonajeDTO dto){

       Personaje entity = personajeMapper.dto2Entity(dto);
       Personaje entitySaved = personajeRepository.save(entity);
       PersonajeDTO result = personajeMapper.entity2DTO(entity,false);
        System.out.println("GUARDAR GENERO");
        return result;

    }


}
