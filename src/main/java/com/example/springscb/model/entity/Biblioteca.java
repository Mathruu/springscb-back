package com.example.springscb.model.entity;


import org.hibernate.annotations.ManyToAny;
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

public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;
    private Long telefone;
    private Long enderecoCep;

    @ManyToOne
    private Livro livro;

    //private List<Livro> livros;
    //private List<Cliente> clientes;
    //private List<Funcionario> funcionarios;




}
