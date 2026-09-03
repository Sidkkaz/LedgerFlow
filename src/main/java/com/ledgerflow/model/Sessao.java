package com.ledgerflow.model;

public class Sessao{

    public Usuario userAtual;

    public Sessao(Usuario userAtual){
        this.userAtual = userAtual;
    }

    public Usuario getUserAtual(){
        return userAtual;
    }
}
