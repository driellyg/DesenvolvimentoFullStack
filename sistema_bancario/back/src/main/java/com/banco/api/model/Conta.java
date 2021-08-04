package com.banco.api.model;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "contas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipoConta",
        discriminatorType = DiscriminatorType.STRING
)
public class Conta {

    @Id
    @GeneratedValue
    protected Long numeroConta;

    @OneToOne
    protected Pessoa cliente;

    protected Double saldo;

    public Conta() {
    }

    public Conta(Long numeroConta, Pessoa cliente, Double saldo) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public Long getNumeroConta() {
        return this.numeroConta;
    }

    public Pessoa getCliente() {
        return this.cliente;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta conta = (Conta) o;
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
        return ("Conta {" + "numeroConta=" + this.numeroConta + "'}");
    }
}
