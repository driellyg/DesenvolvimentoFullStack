package com.banco.api.controller;

import com.banco.api.exceptions.PessoaNotFoundException;
import com.banco.api.model.PessoaJuridica;
import com.banco.api.repository.PessoaJuridicaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PessoaJuridicaController {

    private final PessoaJuridicaRepository repository;

    PessoaJuridicaController(PessoaJuridicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pj")
    List<PessoaJuridica> all() {
        return repository.findAll();
    }

    @PostMapping("/pj")
    PessoaJuridica newPessoa(@RequestBody PessoaJuridica newPessoa) {
        return repository.save(newPessoa);
    }

    @GetMapping("/pj/{id}")
    PessoaJuridica one(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
    }

    @DeleteMapping("/pj/{id}")
    void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
