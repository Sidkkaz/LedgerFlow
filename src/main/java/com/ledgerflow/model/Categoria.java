package com.ledgerflow.model;

public class Categoria {

    private Long id;
    private String nome;
    private final TipoLancamento tipo;

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
    public TipoLancamento setTipo(TipoLancamento tipo) {
        return tipo;
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
