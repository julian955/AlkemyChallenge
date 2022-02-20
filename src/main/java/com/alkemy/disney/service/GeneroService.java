package com.alkemy.disney.service;

import com.alkemy.disney.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

     GeneroDTO save(GeneroDTO dto);

     List<GeneroDTO> returnList();

     void delete(Long id);


}
