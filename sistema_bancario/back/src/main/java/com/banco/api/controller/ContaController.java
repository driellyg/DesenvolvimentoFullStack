package com.banco.api.controller;

import com.banco.api.exceptions.ContaNotFoundException;
import com.banco.api.model.Conta;

import java.util.List;
import java.util.Objects;

import com.banco.api.model.Pessoa;
import com.banco.api.model.PessoaFisica;
import com.banco.api.model.PessoaJuridica;
import com.banco.api.repository.ContaRepository;
import com.banco.api.repository.PessoaFisicaRepository;
import com.banco.api.repository.PessoaJuridicaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContaController {

    private final ContaRepository contaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    ContaController(ContaRepository contaRepository, PessoaFisicaRepository pessoaFisicaRepository,
                    PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.contaRepository = contaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @GetMapping("/conta/{numeroConta}/cpf/{cpf}")
    Conta getByNumeroContaAndCpf(@PathVariable Long numeroConta, @PathVariable String cpf) {
        Conta conta = this.contaRepository.findByNumeroConta(numeroConta);

        if (conta == null) {
            throw new ContaNotFoundException(numeroConta);
        }

        List<PessoaFisica> pessoas = (List<PessoaFisica>) this.pessoaFisicaRepository.findBycpf(cpf);

        PessoaFisica cliente = (PessoaFisica) conta.getCliente();
        Pessoa pessoa = pessoas.stream().filter(p -> Objects.equals(cliente.getCpf(), p.getCpf()) == true).findFirst().orElse(null);

        if (pessoa != null) {
            return conta;
        }

        throw new ContaNotFoundException(numeroConta);
    }

    @GetMapping("/conta/{numeroConta}/cnpj/{cnpj}")
    Conta getByNumeroContaAndCnpj(@PathVariable Long numeroConta, @PathVariable String cnpj) {
        Conta conta = this.contaRepository.findByNumeroConta(numeroConta);

        if (conta == null) {
            throw new ContaNotFoundException(numeroConta);
        }

        List<PessoaJuridica> pessoas = (List<PessoaJuridica>) this.pessoaJuridicaRepository.findByCnpj(cnpj);

        PessoaJuridica cliente = (PessoaJuridica) conta.getCliente();
        Pessoa pessoa = pessoas.stream().filter(p -> Objects.equals(cliente.getCnpj(), p.getCnpj()) == true).findFirst().orElse(null);

        if (pessoa != null) {
            return conta;
        }

        throw new ContaNotFoundException(numeroConta);
    }
}
