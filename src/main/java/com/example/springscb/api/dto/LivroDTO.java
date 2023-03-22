package com.example.springscb.api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import com.example.springscb.model.entity.Livro;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LivroDTO {

    private long id;
    private String titulo;
    private String autor;
    private String editora;
    private String descricao;

    public static LivroDTO create(Livro livro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(livro, LivroDTO.class);
    }
}
