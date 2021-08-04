package com.banco.api.controller;

import com.banco.api.exceptions.ContaNotFoundException;
import java.util.List;
import com.banco.api.model.ContaEspecial;
import com.banco.api.repository.ContaEspecialRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContaEspecialController {

    private final ContaEspecialRepository repository;

    ContaEspecialController(ContaEspecialRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/ce")
    List<ContaEspecial> all() {
        return repository.findAll();
    }

    @PostMapping("/ce")
    ContaEspecial newPessoa(@RequestBody ContaEspecial newPessoa) {
        return repository.save(newPessoa);
    }

    @GetMapping("/ce/{id}")
    ContaEspecial one(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ContaNotFoundException(id));
    }

    @DeleteMapping("/ce/{id}")
    void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }
}