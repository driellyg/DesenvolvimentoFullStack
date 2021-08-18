package br.ufg.inf.fs20211.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_hospede")
public class Hospede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospede")
    private Integer idHospede;

    @Column(name = "nm_hospede")
    private String nmHospede;

    @Column(name = "dt_nascimento")
    private java.sql.Date dtNascimento;
    
    private Integer cpf;

    public Hospede() {}

    public Hospede(Integer idHospede) {
        this.idHospede = idHospede;
    }

    public Hospede(String nmHospede, java.sql.Date dtNascimento, Integer cpf) {
        this.nmHospede = nmHospede;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
    }

    public Hospede(Integer idHospede, String nmHospede, java.sql.Date dtNascimento, Integer cpf) {
        this.idHospede = idHospede;
        this.nmHospede = nmHospede;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
    }

    public void setIdHospede(Integer idHospede) {
        this.idHospede = idHospede;
    }

    public Integer getIdHospede() {
        return idHospede;
    }

    public void setNmHospede(String nmHospede) {
        this.nmHospede = nmHospede;
    }

    public String getNmHospede() {
        return nmHospede;
    }

    public void setDtNascimento(java.sql.Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public java.sql.Date getDtNascimento() {
        return dtNascimento;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Integer getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Hospede [idHospede=" + idHospede + ", nmHospede=" + nmHospede + ", dtNascimento=" + dtNascimento + ", cpf=" + cpf + "]";
    }
}
