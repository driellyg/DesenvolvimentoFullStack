package com.banco.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("cp")
public class ContaPoupanca extends Conta {

    protected Double taxaCorrecao;

    @Column(name="tipo")
    private final String tipo = "cp";

    public ContaPoupanca() {
    }

    public ContaPoupanca(Long numeroConta, Pessoa cliente, Double saldo, Double taxaCorrecao) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.taxaCorrecao = taxaCorrecao;
    }

    public Double getTaxaCorrecao() {
        return this.taxaCorrecao;
    }

    public void setTaxaCorrecao(Double taxaCorrecao) {
        this.taxaCorrecao = taxaCorrecao;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaPoupanca)) return false;
        ContaPoupanca conta = (ContaPoupanca) o;
        return (
                Objects.equals(this.numeroConta, conta.numeroConta)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroConta);
    }

    @Override
    public String toString() {
        return ("Conta poupança {" + "numeroConta=" + this.numeroConta + "'}");
    }
}
