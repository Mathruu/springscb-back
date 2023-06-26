package com.example.springscb.api.dto;
import com.example.springscb.model.entity.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {
    
    private long id;
    private String nome;
    private Long cpf;
    private int enderecoCep;
    private String email;
    private Integer salario;
    private Integer vendas;

    public static FuncionarioDTO create(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        return dto;
    }
}
