package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springscb.model.entity.Reservacompra;
import com.example.springscb.model.repository.ReservacompraRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservacompraService {
    
    private ReservacompraRepository repository;

    public ReservacompraService(ReservacompraRepository repository) {
        this.repository = repository;
    }

    public List<Reservacompra> getReservacompras() {
        return repository.findAll();
    }

    public Optional<Reservacompra> getReservacompraById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Reservacompra salvar(Reservacompra reservacompra) {
        return repository.save(reservacompra);
    }
    
    @Transactional
    public void excluir(Reservacompra reservacompra) {
        Objects.requireNonNull(reservacompra.getId());
        repository.delete(reservacompra);
    }
}
