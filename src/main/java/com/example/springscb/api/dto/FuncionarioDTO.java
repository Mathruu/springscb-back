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
    private Integer cpf;
    private Integer cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String email;
    private Integer telefone;
    private Integer salario;
    private Integer vendas;

    public static FuncionarioDTO create(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        dto.cep = funcionario.getEndereco().getCep();
        dto.rua = funcionario.getEndereco().getRua();
        dto.numero = funcionario.getEndereco().getNumero();
        dto.bairro = funcionario.getEndereco().getBairro();
        dto.cidade = funcionario.getEndereco().getCidade();
        dto.estado = funcionario.getEndereco().getEstado();
        return dto;
    }
}
