package br.ufg.inf.fs.entities;

public class Hotel {
    
    private Integer idHotel;
    private String nmHotel;
    private String endereco;
    private Integer qtdEstrelas;

    public Hotel() { }

    public Hotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Hotel(String nmHotel, String endereco, Integer qtdEstrelas) {
        this.nmHotel = nmHotel;
        this.endereco = endereco;
        this.qtdEstrelas = qtdEstrelas;
    }

    public Hotel(Integer idHotel, String nmHotel, String endereco, Integer qtdEstrelas) {
        this.idHotel = idHotel;
        this.nmHotel = nmHotel;
        this.endereco = endereco;
        this.qtdEstrelas = qtdEstrelas;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setNmHotel(String nmHotel) {
        this.nmHotel = nmHotel;
    }

    public String getNmHotel() {
        return nmHotel;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setQtdEstrelas(Integer qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    public Integer getQtdEstrelas() {
        return qtdEstrelas;
    }

    @Override
    public String toString() {
        return "Hotel [idHotel=" + idHotel + ", nmHotel=" + nmHotel + ", endereco=" + endereco + ", qtdEstrelas="
				+ qtdEstrelas + "]";
    }
}

