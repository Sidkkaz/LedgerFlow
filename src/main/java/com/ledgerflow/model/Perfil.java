package com.ledgerflow.model;

//Configurações do Usuario
public class Perfil {
    int id;

    public Perfil(int id) {
        this.id = id;
    }

    public static Perfil EnviarPerfil(int perfil){
        return new Perfil(perfil);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
