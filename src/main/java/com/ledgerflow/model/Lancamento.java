package com.ledgerflow.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamento {

    int id;
    LocalDate data;
    String descricao;
    BigDecimal valor;
    TipoLancamento tipo;
    Categoria categoria;
    ContaFinanceira conta;
    StatusLancamento status;
    String observacao;

    public Lancamento(
            LocalDate data,
            String descricao,
            BigDecimal valor,
            TipoLancamento tipo,
            Categoria categoria,
            ContaFinanceira conta,
            StatusLancamento status,
            String observacao
    ){
            this.data = data;
            this.descricao = descricao;
            this.valor = valor;
            this.tipo = tipo;
            this.categoria = categoria;
            this.conta = conta;
            this.status = status;
            this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContaFinanceira getConta() {
        return conta;
    }

    public void setConta(ContaFinanceira conta) {
        this.conta = conta;
    }

    public StatusLancamento getStatus() {
        return status;
    }

    public void setStatus(StatusLancamento status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}

