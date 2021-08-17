package br.ufg.inf.fs.entities;

public class Hospede {

    private Integer idHospede;
    private String nmHospede;
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
