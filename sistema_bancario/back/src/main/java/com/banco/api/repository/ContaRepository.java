package com.banco.api.repository;

import com.banco.api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByNumeroConta(Long numeroConta);
}