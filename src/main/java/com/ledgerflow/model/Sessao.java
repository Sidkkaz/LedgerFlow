package com.ledgerflow.model;

public class Sessao{

    private final Usuario userAtual;

    public Sessao(Usuario userAtual){
        this.userAtual = userAtual;
    }

    public Usuario getUserAtual(){
        return userAtual;
    }
}
