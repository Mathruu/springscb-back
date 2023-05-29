package com.example.springscb.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springscb.api.dto.LivroDTO;
import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Endereco;
import com.example.springscb.model.entity.Livro;
import com.example.springscb.service.BibliotecaService;
import com.example.springscb.service.EnderecoService;
import com.example.springscb.service.LivroService;
import com.example.springscb.service.ReservacompraService;
import com.example.springscb.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/livros")
@RequiredArgsConstructor

public class LivroController {

    private final LivroService service;
    private final EnderecoService enderecoService;
    private final ReservacompraService reservacompraService;
    private final UsuarioService usuarioService;
    private final BibliotecaService bibliotecaService;


    @GetMapping
    public ResponseEntity get() {
        List<Livro> livros = service.getLivros();
        return ResponseEntity.ok(livros.stream().map(LivroDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id")Long id) {
        Optional<Livro> livro = service.getLivroById(id);
        if(!livro.isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(livro.map(LivroDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody LivroDTO dto) {
        try {
            Livro livro = converter(dto);
            livro = service.salvar(livro);
            return new ResponseEntity(livro, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping({"{id}"})
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LivroDTO dto) {
        if(!service.getLivroById(id).isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Livro livro = converter(dto);
            livro.setId(id);
            service.salvar(livro);
            return ResponseEntity.ok(livro);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Livro> livro = service.getLivroById(id);
        if(!livro.isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(livro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Livro converter(LivroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Livro.class);
    }
}

