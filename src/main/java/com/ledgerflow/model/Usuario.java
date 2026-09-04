package com.ledgerflow.model;

// Usuario, quem detem a licença e uso do software;
public class Usuario {

    long id;
    String nome;
    String email;
    String senha;

    public Usuario(String nome, String email, String senha) {
        if(nome.isBlank() || email.isBlank() || senha.isBlank()) throw new RuntimeException("Campo vazio");

        if(nome.length() < 3) throw new RuntimeException("Nome deve ter 3 caracteres");
        this.nome = nome;

        if(!email.contains("@") && email.length() <= 3) throw new RuntimeException("Email invalido");
        this.email = email;

        if(senha.length() < 8) throw new RuntimeException("Minímo 8 caracteres");
        this.senha = senha;
    }

    public void AlterarSenha(String senha){
        this.senha = senha;
    }

    //region Get/Set

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

//endregion

}
