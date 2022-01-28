package com.alkemy.disney.service.imp;
import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.mapper.GeneroMapper;
import com.alkemy.disney.repository.GeneroRepository;
import com.alkemy.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImp implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;


    public GeneroDTO save(GeneroDTO dto){
        Genero entity = generoMapper.generoDTO2Entity(dto);
        Genero entitySaved = generoRepository.save(entity);
        GeneroDTO result = generoMapper.generoEntity2DTO(entitySaved);
        System.out.println("GUARDAR GENERO");
        return result;
    }

    public List<GeneroDTO> returnList(){
        List<Genero> entityList = generoRepository.findAll();
        List<GeneroDTO> dtoList = generoMapper.listEntity2DTO(entityList);

        return dtoList;
    }

    public void delete (Long id){
        generoRepository.deleteById(id);
    }
}
