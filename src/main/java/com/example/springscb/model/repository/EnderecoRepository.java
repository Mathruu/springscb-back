package com.example.springscb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springscb.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
