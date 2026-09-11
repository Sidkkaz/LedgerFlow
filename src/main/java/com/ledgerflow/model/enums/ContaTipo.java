package com.ledgerflow.model.enums;

public enum ContaTipo{
    Corrente(1),
    Poupanca(2),
    Dinheiro(3);

    private final int valor;

    ContaTipo(int valor) {
        this.valor = valor;
    }

    public int getValue() {
        return valor;
    }

    public static ContaTipo fromValue(int valor) {
        for (ContaTipo contaTipo : values()) {
            if(contaTipo.getValue() == valor){
                return contaTipo;
            }
        }
        throw new IllegalArgumentException("Tipo invalido: " + valor);
    }

}
