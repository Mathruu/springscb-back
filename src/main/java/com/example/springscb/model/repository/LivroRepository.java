package com.example.springscb.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springscb.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    Optional <Livro> findByTitulo(String titulo);
}
