package com.example.springscb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springscb.model.entity.Endereco;
import com.example.springscb.model.repository.EnderecoRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
    
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }
}
