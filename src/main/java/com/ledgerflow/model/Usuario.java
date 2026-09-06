package com.ledgerflow.model;

public class Usuario {

    long id;
    String nome;
    String email;
    String senha;

    public Usuario(
            Long id,
            String nome,
            String email,
            String senha
    ) {
        validarCriacao(
             nome,
             email,
             senha
        );

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void AlterarSenha(String senha){
        this.senha = senha;
    }

    private void validarCriacao(
            String nome,
            String email,
            String senha
    ){
        if(nome == null || nome.isBlank())
            throw new RuntimeException("Nome vazio");

        if(email == null || email.isBlank())
            throw new RuntimeException("Email vazio");

        if(senha == null || senha.isBlank())
            throw new RuntimeException("Senha vazio");

        if(nome.length() < 3)
            throw new RuntimeException("Nome deve ter 3 caracteres");

        if(!email.contains("@") && email.length() <= 3)
            throw new RuntimeException("Email invalido");

        if(senha.length() < 8)
            throw new RuntimeException("Minímo 8 caracteres");
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
