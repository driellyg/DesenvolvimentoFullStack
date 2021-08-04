package com.banco.api.model;

import com.banco.api.enums.OperacaoEnum;

import java.util.Map;

public class Operacao {

    protected OperacaoEnum operacao;
    protected Map<String, Object> payload;

    public Operacao() {}

    public Operacao(OperacaoEnum operacao, Map<String, Object> payload) {
        this.operacao = operacao;
        this.payload = payload;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setOperacao(OperacaoEnum operacao) {
        this.operacao = operacao;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Operacao{" +
                "operacao=" + operacao +
                ", payload=" + payload +
                '}';
    }
}
