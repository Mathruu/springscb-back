package com.example.springscb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springscb.model.entity.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
    
}
