package com.example.springscb.api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import com.example.springscb.model.entity.Biblioteca;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BibliotecaDTO {
    private String nome;
    private Integer telefone;


    public static BibliotecaDTO create(Biblioteca Biblioteca){
        ModelMapper modelMapper = new ModelMapper();
        BibliotecaDTO dto = modelMapper.map(Biblioteca, BibliotecaDTO.class);
        return dto;
    }
}
