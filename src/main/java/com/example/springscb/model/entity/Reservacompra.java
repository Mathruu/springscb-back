package com.example.springscb.model.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reservacompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    private String status;
    private float valor;
    private Boolean compra;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date data;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Cliente cliente;

    public void definirNovaData() {
        this.data = new Date();
    }
}

