package com.ledgerflow.model;

public enum StatusLancamento {
    Previsto(1),
    Confirmado(2),
    Cancelado(3);

    private final int valor;
    StatusLancamento(int valor) {
        this.valor = valor;
    }
    public int getValue() {
        return valor;
    }

    public static StatusLancamento Select(int valor) {
        if (valor == 1){
            return StatusLancamento.Previsto;
        }else if (valor == 2){
            return StatusLancamento.Confirmado;
        }else if (valor == 3){
            return StatusLancamento.Cancelado;
        }else{
            return null;
        }
    }

    public static int WhoIs(StatusLancamento t){
        if (t == StatusLancamento.Previsto){
            return 1;
        }
        else if (t == StatusLancamento.Confirmado){
            return 2;
        }else if (t == StatusLancamento.Cancelado){
            return 3;
        }else {
            return 0;
        }
    }
}
