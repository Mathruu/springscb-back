package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Funcionario;
import com.example.springscb.model.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }

    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }

    public void validar(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (funcionario.getCpf() == null){
            throw new RegraNegocioException("CPF inválido");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().trim().equals("")) {
            throw new RegraNegocioException("Email inválido");
        }
        if (funcionario.getSalario() == null){
            throw new RegraNegocioException("Salário inválido");
        }
        if (funcionario.getVendas() == null){
            throw new RegraNegocioException("Vendas inválidas");
        }
    }
}
