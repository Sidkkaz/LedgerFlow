package com.ledgerflow.model;

public enum ContaTipo{
    Corrente(1),
    Poupanca(2);

    private final int valor;
    ContaTipo(int valor) {
        this.valor = valor;
    }
    public int getValue() {
        return valor;
    }

    public static ContaTipo Select(int valor) {
        if (valor == 1){
            return ContaTipo.Corrente;
        }else if (valor == 2){
            return ContaTipo.Poupanca;
        }else {
            return null;
        }
    }

    public static int WhoIs(ContaTipo t){
        if (t == ContaTipo.Corrente){
            return 1;
        }
        else if (t == ContaTipo.Poupanca){
            return 2;
        }else return 0;
    }
}
