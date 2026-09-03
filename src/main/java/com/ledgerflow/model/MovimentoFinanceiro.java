package com.ledgerflow.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoFinanceiro {

    private final ContaFinanceira conta;
    private final BigDecimal valor;
    private final TipoLancamento tipoLancamento;

    public MovimentoFinanceiro(
         ContaFinanceira conta,
         BigDecimal valor,
         TipoLancamento tipoLancamento
    ){
         this.conta = conta;
         this.valor = valor;
         this.tipoLancamento = tipoLancamento;
    }

    public ContaFinanceira getConta() {
        return conta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }


}
