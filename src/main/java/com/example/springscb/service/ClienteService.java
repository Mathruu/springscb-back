package com.example.springscb.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springscb.exception.RegraNegocioException;
import com.example.springscb.model.entity.Cliente;
import com.example.springscb.model.entity.Livro;
import com.example.springscb.model.repository.ClienteRepository;
import com.example.springscb.model.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private ClienteRepository repository;
    @Autowired
    private LivroRepository LivroRepository;


    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        validar(cliente);
        return repository.save(cliente);
    }

    @Transactional
    public Cliente salvarClienteComTituloLivro(Cliente cliente, String tituloLivro) {
        Livro livro = LivroRepository.findByTitulo(tituloLivro)
            .orElseThrow(() -> new RegraNegocioException("Livro não encontrado"));
        cliente.setLivro(livro);
        validar(cliente);
        return repository.save(cliente);
    }

    @Transactional
    public void excluir(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        repository.delete(cliente);
    }

    public void validar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (cliente.getCpf() == null) {
            throw new RegraNegocioException("CPF inválido");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().equals("")) {
            throw new RegraNegocioException("Email inválido");
        }
        if (String.valueOf(cliente.getEnderecoCep()).length() != 8) {
            throw new RegraNegocioException("CEP inválido");
        }

        if (cliente.getLivro() == null || cliente.getLivro().getId() == null || cliente.getLivro().getId() == 0) {
            throw new RegraNegocioException("Livro inválido");
        }

    }
}
