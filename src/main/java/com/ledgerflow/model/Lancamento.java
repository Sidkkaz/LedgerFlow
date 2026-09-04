package com.ledgerflow.model;

import com.ledgerflow.model.enums.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamento {

    long id;
    LocalDate data;
    String descricao;
    BigDecimal valor;
    TipoLancamento tipo;
    Categoria categoria;
    ContaFinanceira conta;
    String observacao;

    public Lancamento(
            LocalDate data,
            String descricao,
            BigDecimal valor,
            TipoLancamento tipo,
            Categoria categoria,
            ContaFinanceira conta,
            String observacao
    ){
            this.data = data;
            this.descricao = descricao;
            this.valor = valor;
            this.tipo = tipo;
            this.categoria = categoria;
            this.conta = conta;
            this.observacao = observacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public TipoLancamento getTipo() {
        return tipo;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}

