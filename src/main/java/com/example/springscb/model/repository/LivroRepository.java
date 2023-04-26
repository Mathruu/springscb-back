package com.example.springscb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springscb.model.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
