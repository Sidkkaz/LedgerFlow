package com.ledgerflow.model;

import com.ledgerflow.model.enums.TipoLancamento;

public class Categoria {

    private long id;
    private final String nome;
    private TipoLancamento tipo;
    private boolean ativo;

    public Categoria(Long id, String nome, TipoLancamento tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public static Categoria indefindo(){
        return new Categoria(1L, "Indefinido", null);
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

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    //endregion
}
