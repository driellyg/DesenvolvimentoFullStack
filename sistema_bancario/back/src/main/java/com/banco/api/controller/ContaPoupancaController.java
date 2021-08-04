package com.banco.api.controller;

import com.banco.api.exceptions.ContaNotFoundException;
import com.banco.api.model.ContaPoupanca;
import com.banco.api.repository.ContaPoupancaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContaPoupancaController {

    private final ContaPoupancaRepository repository;

    ContaPoupancaController(ContaPoupancaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cp")
    List<ContaPoupanca> all() {
        return repository.findAll();
    }

    @PostMapping("/cp")
    ContaPoupanca newPessoa(@RequestBody ContaPoupanca newPessoa) {
        return repository.save(newPessoa);
    }

    @GetMapping("/cp/{id}")
    ContaPoupanca one(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ContaNotFoundException(id));
    }

    @DeleteMapping("/cp/{id}")
    void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }
}