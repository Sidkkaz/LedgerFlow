package com.ledgerflow.model;

public class ContaFinanceira {

    int id;
    String nome;
    int agencia;
    int numero;
    ContaTipo tipo;
    double saldo;
    boolean ativo;

    public ContaFinanceira(
            String nome,
            int agencia,
            int numero,
            ContaTipo tipo,
            double saldo,
            boolean ativo
    ){

        this.nome = nome;
        this.agencia = agencia;
        this.numero = numero;
        this.tipo =  tipo;
        this.saldo = saldo;
        this.ativo = ativo;
    }


    public static ContaFinanceira create(int id){
        return new ContaFinanceira("",0,0,ContaTipo.corrente,1,true);
    }


    //getter e setter
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

