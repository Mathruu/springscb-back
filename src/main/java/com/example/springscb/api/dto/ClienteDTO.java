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
    private Integer cpf;
    private Integer cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String email;

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        dto.cep = cliente.getEndereco().getCep();
        dto.rua = cliente.getEndereco().getRua();
        dto.numero = cliente.getEndereco().getNumero();
        dto.bairro = cliente.getEndereco().getBairro();
        dto.cidade = cliente.getEndereco().getCidade();
        dto.estado = cliente.getEndereco().getEstado();
        return dto;
    } 
}
