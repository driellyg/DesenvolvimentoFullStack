package com.banco.api.model;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "pessoas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
  name = "tipoPessoa",
  discriminatorType = DiscriminatorType.STRING
)
public class Pessoa {

  @Id
  @GeneratedValue
  protected Long id;

  protected String nome;
  protected String endereco;

  public Pessoa() {}

  Pessoa(String nome, String endereco) {
    this.nome = nome;
    this.endereco = endereco;
  }

  public Long getId() {
    return this.id;
  }

  public String getNome() {
    return this.nome;
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pessoa)) return false;
    Pessoa pessoa = (Pessoa) o;
    return (
      Objects.equals(this.id, pessoa.id) &&
      Objects.equals(this.nome, pessoa.nome) &&
      Objects.equals(this.endereco, pessoa.endereco)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.nome, this.endereco);
  }

  @Override
  public String toString() {
    return ("Pessoa {" + "id=" + this.id + ", name='" + this.nome + "'}");
  }
}
