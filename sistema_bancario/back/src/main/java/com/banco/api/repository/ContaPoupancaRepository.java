package com.banco.api.repository;

import com.banco.api.model.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Long> {
    ContaPoupanca findByNumeroConta(Long numeroConta);
}