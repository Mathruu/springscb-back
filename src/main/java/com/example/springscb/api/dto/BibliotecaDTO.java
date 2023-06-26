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

    private long id;
    private String nome;
    private Long telefone;
    private long enderecoCep;
    private String tituloLivro;


    public static BibliotecaDTO create(Biblioteca Biblioteca){
        ModelMapper modelMapper = new ModelMapper();
        BibliotecaDTO dto = modelMapper.map(Biblioteca, BibliotecaDTO.class);
        dto.tituloLivro = Biblioteca.getLivro().getTitulo();
        return dto;
    }
}
