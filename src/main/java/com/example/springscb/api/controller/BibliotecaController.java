package com.example.springscb.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springscb.api.dto.BibliotecaDTO;
import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Biblioteca;
import com.example.springscb.model.entity.Livro;
import com.example.springscb.service.BibliotecaService;
import com.example.springscb.service.LivroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bibliotecas")
@RequiredArgsConstructor
@CrossOrigin

public class BibliotecaController {
    
    private final BibliotecaService service;
    private final LivroService livroService;

    @GetMapping()
    public ResponseEntity get() {
        List<Biblioteca> bibliotecas = service.getBibliotecas();
        return ResponseEntity.ok(bibliotecas.stream().map(BibliotecaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id")Long id) {
        Optional<Biblioteca> biblioteca = service.getBibliotecaById(id);
        if(!biblioteca.isPresent()) {
            return new ResponseEntity("Biblioteca não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(biblioteca.map(BibliotecaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody BibliotecaDTO dto) {
        try {
            Biblioteca biblioteca = converter(dto);
            biblioteca = service.salvarBibliotecaComTituloLivro(biblioteca, dto.getTituloLivro());
            return new ResponseEntity(biblioteca, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody BibliotecaDTO dto) {
        if(!service.getBibliotecaById(id).isPresent()) {
            return new ResponseEntity("Biblioteca não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Biblioteca biblioteca = converter(dto);
            biblioteca.setId(id);
            service.salvar(biblioteca);
            return ResponseEntity.ok(biblioteca);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Biblioteca> biblioteca = service.getBibliotecaById(id);
        if(!biblioteca.isPresent()) {
            return new ResponseEntity("Biblioteca não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(biblioteca.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Biblioteca converter(BibliotecaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Biblioteca biblioteca = modelMapper.map(dto, Biblioteca.class);
        Livro livro = modelMapper.map(dto, Livro.class);
        livro.setTitulo(dto.getTituloLivro());
        biblioteca.setLivro(livro);
        return biblioteca;
    }

}
