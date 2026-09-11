package com.ledgerflow.model.enums;

public enum TipoLancamento {
    Receita(1),
    Despesa(2),
    Transferencia(3),
    Indefinido(4);

    private final int valor;

    TipoLancamento(int valor) {
        this.valor = valor;
    }

    public int getValue() {
        return valor;
    }

    public static TipoLancamento fromValue(int valor) {
        for (TipoLancamento tipo : values()) {
            if(tipo.getValue() == valor){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo invalido: " + valor);
    }

}
