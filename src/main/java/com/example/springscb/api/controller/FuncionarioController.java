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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springscb.api.dto.FuncionarioDTO;
import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Endereco;
import com.example.springscb.model.entity.Funcionario;
import com.example.springscb.service.EnderecoService;
import com.example.springscb.service.FuncionarioService;
import com.example.springscb.service.LivroService;
import com.example.springscb.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor

public class FuncionarioController {
    
    private final FuncionarioService service;
    private final UsuarioService usuarioService;
    private final LivroService livroService;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id")Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if(!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            Endereco endereco = enderecoService.salvar(funcionario.getEndereco());;
            funcionario.setEndereco(endereco);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, FuncionarioDTO dto) {
        if(!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            Endereco endereco = enderecoService.salvar(funcionario.getEndereco());
            funcionario.setEndereco(endereco);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if(!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario converter(FuncionarioDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        funcionario.setEndereco(endereco);
        return funcionario;
    }
}
