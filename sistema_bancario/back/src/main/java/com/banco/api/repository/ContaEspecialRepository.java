package com.banco.api.repository;

import com.banco.api.model.ContaEspecial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaEspecialRepository extends JpaRepository<ContaEspecial, Long> {
    ContaEspecial findByNumeroConta(Long numeroConta);
}