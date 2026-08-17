package com.ledgerflow.model;

public class ContaFinanceira {

    int id;
    String nome;
    String banco;
    int agencia;
    int numero;
    ContaTipo tipo;
    double saldo;
    boolean ativo;

    public ContaFinanceira(
            int id,
            String nome,
            String banco,
            int agencia,
            int numero,
            ContaTipo tipo,
            double saldo,
            boolean ativo
    ){

        this.id = id;
        this.nome = nome;
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.tipo =  tipo;
        this.saldo = saldo;
        this.ativo = ativo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ContaTipo getTipo() {
        return tipo;
    }

    public void setTipo(ContaTipo tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

