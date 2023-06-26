package com.example.springscb.api.dto;
import com.example.springscb.model.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    
    private long id;
    private String nome;
    private Long cpf;
    private long enderecoCep;
    private String email;
    private String tituloLivro;


    public static ClienteDTO create(Cliente cliente) {
    ModelMapper modelMapper = new ModelMapper();
    ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
    dto.tituloLivro = cliente.getLivro().getTitulo();

    // Livro livro = cliente.getLivro();
    // if (livro != null) {
    //     dto.idLivro = livro.getTitulo();
    // } else {
    //     dto.idLivro = null;
    // }
    
    return dto;

    } 
}
