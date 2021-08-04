package com.banco.api.repository;

import com.banco.api.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    List<PessoaFisica> findBycpf(String cpf);
}

