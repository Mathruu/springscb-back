package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Biblioteca;
import com.example.springscb.model.repository.BibliotecaRepository;

import jakarta.transaction.Transactional;

@Service    
public class BibliotecaService {
    private BibliotecaRepository repository;
    
    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public List<Biblioteca> getBibliotecas() {
        return repository.findAll();
    }

    public Optional<Biblioteca> getBibliotecaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Biblioteca salvar(Biblioteca biblioteca) {
        validar(biblioteca);
        return repository.save(biblioteca);
    }

    @Transactional
    public void excluir(Biblioteca biblioteca) {
        Objects.requireNonNull(biblioteca.getId());
        repository.delete(biblioteca);
    }

    public void validar(Biblioteca biblioteca) {
        if (biblioteca.getEndereco() == null || biblioteca.getEndereco().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }
}
