package com.banco.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("ce")
public class ContaEspecial extends Conta {

    protected Double limite;

    @Column(name="tipo")
    private final String tipo = "ce";

    public ContaEspecial() {
    }

    public ContaEspecial(Long numeroConta, Pessoa cliente, Double saldo, Double limite) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
    }

    public Double getLimite() {
        return this.limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaEspecial)) return false;
        ContaEspecial conta = (ContaEspecial) o;
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
        return ("Conta especial {" + "numeroConta=" + this.numeroConta + "'}");
    }
}
