package com.example.springscb.api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import org.modelmapper.ModelMapper;
import com.example.springscb.model.entity.Reservacompra;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservacompraDTO {
    
    private String status;
    private Integer valor;
    private Boolean compra;
    private Date data;

    public static ReservacompraDTO create(Reservacompra reservacompra) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservacompra, ReservacompraDTO.class);
    }
}
