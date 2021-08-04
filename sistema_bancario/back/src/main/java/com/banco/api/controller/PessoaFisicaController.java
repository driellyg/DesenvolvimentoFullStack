package com.banco.api.controller;

import java.util.List;

import com.banco.api.exceptions.PessoaNotFoundException;
import com.banco.api.model.PessoaFisica;
import com.banco.api.repository.PessoaFisicaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PessoaFisicaController {

  private final PessoaFisicaRepository repository;

  PessoaFisicaController(PessoaFisicaRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/pf")
  List<PessoaFisica> all() {
    return repository.findAll();
  }

  @PostMapping("/pf")
  PessoaFisica newPessoa(@RequestBody PessoaFisica newPessoa) {
    return repository.save(newPessoa);
  }

  @GetMapping("/pf/{id}")
  PessoaFisica one(@PathVariable Long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new PessoaNotFoundException(id));
  }

  @DeleteMapping("/pf/{id}")
  void deletePessoa(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
