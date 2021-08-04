package com.banco.api.repository;

import com.banco.api.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    List<PessoaJuridica> findByCnpj(String cnpj);
}

