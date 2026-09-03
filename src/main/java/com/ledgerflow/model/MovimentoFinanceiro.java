package com.ledgerflow.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoFinanceiro {

    private Long id;
    private ContaFinanceira conta;
    private Lancamento lancamento;
    private BigDecimal valor;
    private TipoLancamento tipoLancamento;
    private LocalDate data;

    public MovimentoFinanceiro(
         Long id,
         ContaFinanceira conta,
         Lancamento lancamento,
         BigDecimal valor,
         TipoLancamento tipoLancamento,
         LocalDate data
    ){
        this.id = id;
        this.conta = conta;
        this.lancamento = lancamento;
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContaFinanceira getConta() {
        return conta;
    }

    public void setConta(ContaFinanceira conta) {
        this.conta = conta;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
