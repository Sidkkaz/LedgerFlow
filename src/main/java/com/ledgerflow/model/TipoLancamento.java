package com.ledgerflow.model;

public enum TipoLancamento {
    Receita(1),
    Despesa(2);

    private final int valor;

    TipoLancamento(int valor) {
        this.valor = valor;
    }
    public int getValue() {
        return valor;
    }

    public static TipoLancamento Select(int valor) {
        if (valor == 1){
            return TipoLancamento.Receita;
        }else if (valor == 2){
            return TipoLancamento.Despesa;
        }else {
            return null;
        }
    }

    public static int WhoIs(TipoLancamento t){
        if (t == TipoLancamento.Receita){
            return 1;
        }
        else if (t == TipoLancamento.Despesa){
            return 2;
        }else return 0;
    }
}
