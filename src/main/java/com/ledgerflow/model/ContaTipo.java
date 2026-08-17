package com.ledgerflow.model;

public enum ContaTipo{
    corrente(1),
    poupanca(2);

    private final int valor;
    ContaTipo(int valor) {
        this.valor = valor;
    }
    public int getValue() {
        return valor;
    }

    public static ContaTipo Select(int valor) {
        if (valor == 1){
            return ContaTipo.corrente;
        }else if (valor == 2){
            return ContaTipo.poupanca;
        }else {
            return null;
        }
    }

    public static int WhoIs(ContaTipo t){
        if (t == ContaTipo.corrente){
            return 1;
        }
        else if (t == ContaTipo.poupanca){
            return 2;
        }else return 0;
    }
}
