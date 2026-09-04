package com.ledgerflow.model;

import com.ledgerflow.model.enums.TipoLancamento;

public class Categoria {

    private long id;
    private String nome;
    private TipoLancamento tipo;

    public Categoria(String nome, TipoLancamento tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public static Categoria Select(int id) {
        return new Categoria("", TipoLancamento.Select(id));
    }

    public TipoLancamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
