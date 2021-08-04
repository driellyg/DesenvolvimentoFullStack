package com.banco.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("pf")
public class PessoaFisica extends Pessoa {

    private String genero;
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    public PessoaFisica() {}

    public PessoaFisica(String nome, String endereco, String genero, String cpf, Date dataNascimento) {
        super(nome, endereco);
        this.genero = genero;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaFisica)) return false;
        PessoaFisica pessoa = (PessoaFisica) o;
        return (
                Objects.equals(this.id, pessoa.id) &&
                        Objects.equals(this.nome, pessoa.nome) &&
                        Objects.equals(this.endereco, pessoa.endereco) &&
                        Objects.equals(this.genero, pessoa.genero) &&
                        Objects.equals(this.cpf, pessoa.cpf) &&
                        Objects.equals(this.dataNascimento, pessoa.dataNascimento)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.endereco, this.genero, this.cpf, this.dataNascimento);
    }

    @Override
    public String toString() {
        return ("PessoaFisica {" + "id=" + this.id + ", name='" + this.nome + "', cpf='" + this.cpf + "'," +
                "nasc='" + this.dataNascimento + "'}");
    }

}

