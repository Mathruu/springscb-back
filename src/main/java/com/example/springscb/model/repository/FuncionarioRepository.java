package com.example.springscb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springscb.model.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
}
