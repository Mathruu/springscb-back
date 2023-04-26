package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Biblioteca;
import com.example.springscb.model.entity.Livro;
import com.example.springscb.model.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {
    
        private LivroRepository repository;
    
        public LivroService(LivroRepository repository) {
            this.repository = repository;
        }
    
        public List<Livro> getLivros() {
            return repository.findAll();
        }
    
        public Optional<Livro> getLivroById(Long id) {
            return repository.findById(id);
        }
    
        @Transactional
        public Livro salvar(Livro livro) {
            validar(livro);
            return repository.save(livro);
        }
    
        @Transactional
        public void excluir(Livro livro) {
            Objects.requireNonNull(livro.getId());
            repository.delete(livro);
        }
    
        public void validar(Livro livro) {
            if (livro.getTitulo() == null || livro.getTitulo().equals("")) {
                throw new RegraNegocioException("Nome inválido");
            }
        }
}
