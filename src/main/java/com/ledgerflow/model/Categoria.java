package com.ledgerflow.model;

import com.ledgerflow.model.enums.TipoLancamento;

public class Categoria {

    private long id;
    private final String nome;
    private TipoLancamento tipo;

    public Categoria(String nome, TipoLancamento tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public static Categoria Select(int id) {
        return new Categoria("", TipoLancamento.Select(id));
    }

    //region Get/Set
    public TipoLancamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    //endregion
}
