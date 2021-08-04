package com.banco.api.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("pj")
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private String atividade;

    public PessoaJuridica() {}

    public PessoaJuridica(String nome, String endereco, String cnpj, String atividade) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.atividade = atividade;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getAtividade() {
        return this.atividade;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaJuridica)) return false;
        PessoaJuridica pessoa = (PessoaJuridica) o;
        return (
                Objects.equals(this.id, pessoa.id) &&
                        Objects.equals(this.nome, pessoa.nome) &&
                        Objects.equals(this.endereco, pessoa.endereco) &&
                        Objects.equals(this.cnpj, pessoa.cnpj) &&
                        Objects.equals(this.atividade, pessoa.atividade)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.endereco, this.cnpj, this.atividade);
    }

    @Override
    public String toString() {
        return ("PessoaJuridica {" + "id=" + this.id + ", name='" + this.nome + "', cnpj='" + this.cnpj + "'}");
    }

}
