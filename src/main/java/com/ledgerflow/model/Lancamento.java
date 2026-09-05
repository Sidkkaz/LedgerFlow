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
            Long id,
            LocalDate data,
            String descricao,
            BigDecimal valor,
            TipoLancamento tipo,
            Categoria categoria,
            ContaFinanceira conta
    ){
        validarCriacao(
                 data,
                 descricao,
                 valor,
                 tipo,
                 conta
        );
            this.id = id;
            this.data = data;
            this.descricao = descricao;
            this.valor = valor;
            this.tipo = tipo;
            this.categoria = categoria == null ? Categoria.indefindo() : categoria;
            this.conta = conta;
    }

    private void validarCriacao(
            LocalDate data,
            String descricao,
            BigDecimal valor,
            TipoLancamento tipo,
            ContaFinanceira conta
    ) {
        if (data == null) {
            throw new IllegalArgumentException("Data é obrigatória");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }

        if (valor == null) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Valor deve ser maior que zero"
            );
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo é obrigatório");
        }

        if (conta == null) {
            throw new IllegalArgumentException(
                    "Conta financeira é obrigatória"
            );
        }

    }


    //region Get/Set
    public long getId() {
        return id;
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
    //endregion

}

