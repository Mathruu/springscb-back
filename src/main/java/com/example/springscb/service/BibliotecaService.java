package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Biblioteca;
import com.example.springscb.model.entity.Livro;
import com.example.springscb.model.repository.BibliotecaRepository;
import com.example.springscb.model.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service    
public class BibliotecaService {
    private BibliotecaRepository repository;

    @Autowired
    private LivroRepository LivroRepository;
    
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
        return repository.save(biblioteca);
    }

    @Transactional
    public Biblioteca salvarBibliotecaComTituloLivro(Biblioteca biblioteca, String tituloLivro) {
        Livro livro = LivroRepository.findByTitulo(tituloLivro)
            .orElseThrow(() -> new RegraNegocioException("Livro não encontrado"));
        biblioteca.setLivro(livro);
        return repository.save(biblioteca);
    }

    @Transactional
    public void excluir(Biblioteca biblioteca) {
        Objects.requireNonNull(biblioteca.getId());
        repository.delete(biblioteca);
    }

    public void validar (Biblioteca biblioteca) {
        if (biblioteca.getNome() == null || biblioteca.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        // if (biblioteca.getLivro() == null || biblioteca.getLivro().getId() == null) {
        //     throw new RegraNegocioException("Livro inválido");
        // }
        if (biblioteca.getTelefone() == null || biblioteca.getTelefone().equals("")) {
            throw new RegraNegocioException("Telefone inválido");
        }
        if (String.valueOf(biblioteca.getEnderecoCep()).length() != 8) {
            throw new RegraNegocioException("CEP inválido");
        }
    }


}
