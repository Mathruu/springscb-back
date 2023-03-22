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
    private Integer cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    public static BibliotecaDTO create(Biblioteca Biblioteca){
        ModelMapper modelMapper = new ModelMapper();
        BibliotecaDTO dto = modelMapper.map(Biblioteca, BibliotecaDTO.class);
        dto.cep = Biblioteca.getEndereco().getCep();
        dto.rua = Biblioteca.getEndereco().getRua();
        dto.numero = Biblioteca.getEndereco().getNumero();
        dto.bairro = Biblioteca.getEndereco().getBairro();
        dto.cidade = Biblioteca.getEndereco().getCidade();
        dto.estado = Biblioteca.getEndereco().getEstado();
        return dto;
    }
}
