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

import com.example.springscb.api.dto.ReservacompraDTO;
import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Reservacompra;
import com.example.springscb.service.ClienteService;
import com.example.springscb.service.LivroService;
import com.example.springscb.service.ReservacompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservacompra")
@RequiredArgsConstructor

public class ReservacompraController {
    
    private final ReservacompraService service;
    private final LivroService livroService;
    private final ClienteService clienteService;

    @GetMapping()
    public ResponseEntity get() {
        List<Reservacompra> reservacompras = service.getReservacompras();
        return ResponseEntity.ok(reservacompras.stream().map(ReservacompraDTO::create).collect(Collectors.toList()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Reservacompra> reservacompra = service.getReservacompraById(id);
        if (!reservacompra.isPresent()) {
            return new ResponseEntity("A Reserva_compra não foi encontrada.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reservacompra.map(ReservacompraDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ReservacompraDTO dto) {
        try {
            Reservacompra reservacompra = converter(dto);
            reservacompra = service.salvar(reservacompra);
            return new ResponseEntity(reservacompra, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ReservacompraDTO dto) {
        if (!service.getReservacompraById(id).isPresent()) {
            return new ResponseEntity("A Reserva_compra não foi encontrada.", HttpStatus.NOT_FOUND);
        }
        try {
            Reservacompra reservacompra = converter(dto);
            reservacompra.setId(id);
            service.salvar(reservacompra);
            return ResponseEntity.ok(reservacompra);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Reservacompra> reservacompra = service.getReservacompraById(id);
        if (!reservacompra.isPresent()) {
            return new ResponseEntity("A Reserva_compra não foi encontrada.", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(reservacompra.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Reservacompra converter(ReservacompraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Reservacompra reservacompra = modelMapper.map(dto, Reservacompra.class);
        return reservacompra;
    }
}
