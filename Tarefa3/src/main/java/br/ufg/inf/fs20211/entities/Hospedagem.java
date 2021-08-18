package br.ufg.inf.fs20211.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_hospedagem")
public class Hospedagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospedagem")
    private Integer idHospedagem;

    @OneToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @OneToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;

    @Column(name = "dt_checkin")
    private java.sql.Date dtCheckin;

    @Column(name = "dt_checkout")
    private java.sql.Date dtCheckout;

    public Hospedagem() {}

    public Hospedagem(Quarto quarto, Hospede hospede, java.sql.Date dtCheckin, java.sql.Date dtCheckout) {
        this.quarto = quarto;
        this.hospede = hospede;
        this.dtCheckin = dtCheckin;
        this.dtCheckout = dtCheckout;
    }

    public Hospedagem(Integer idHospedagem, Quarto quarto, Hospede hospede, java.sql.Date dtCheckin, java.sql.Date dtCheckout) {
        this.idHospedagem = idHospedagem;
        this.quarto = quarto;
        this.hospede = hospede;
        this.dtCheckin = dtCheckin;
        this.dtCheckout = dtCheckout;
    }

    public void setIdHospedagem(Integer idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public Integer getIdHospedagem() {
        return idHospedagem;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setDtCheckin(java.sql.Date dtCheckin) {
        this.dtCheckin = dtCheckin;
    }

    public java.sql.Date getDtCheckin() {
        return dtCheckin;
    }

    public void setDtCheckout(java.sql.Date dtCheckout) {
        this.dtCheckout = dtCheckout;
    }

    public java.sql.Date getDtCheckout() {
        return dtCheckout;
    }

    @Override
    public String toString() {
        return "Hospedagem [idHospedagem=" + idHospedagem + ", quartoId=" + quarto.getIdQuarto() + ", hospedeId=" + hospede.getIdHospede()
                + ", dtCheckin=" + dtCheckin + ", dtCheckout=" + dtCheckout + "]";
    }
}
