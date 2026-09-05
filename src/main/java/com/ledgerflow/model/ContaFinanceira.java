package com.ledgerflow.model;

import com.ledgerflow.model.enums.ContaTipo;

import java.math.BigDecimal;

public class ContaFinanceira {

    private long id;
    private final String nome;
    private int agencia;
    private int numero;
    private final ContaTipo tipo;
    private BigDecimal saldoInicial;
    private BigDecimal saldo;
    boolean ativo;

    public ContaFinanceira(
            Long id,
            String nome,
            ContaTipo tipo,
            BigDecimal saldoInicial,
            BigDecimal saldo
    ){
        validarCriacao(
             nome,
             tipo,
             saldoInicial,
             saldo
        );

        this.nome = nome;
        this.tipo =  tipo;
        this.saldoInicial = saldoInicial;
        this.saldo = saldoInicial.add(saldo);
    }

    public void Depositar(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");

        this.saldo = this.saldo.add(valor);
    }

    public void Sacar(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        if(saldo.compareTo(valor) < 0)
            throw new IllegalArgumentException("Saldo insuficiente");

        this.saldo = this.saldo.subtract(valor);
    }

    public void desativar(){
        this.ativo = false;
    }

    public void ativar(){
        this.ativo = true;
    }

    private void validarCriacao(
            String nome,
            ContaTipo tipo,
            BigDecimal saldoInicial,
            BigDecimal saldo
    ){
        if (nome == null || nome.isBlank()){
            throw new RuntimeException("Nome vazio");
        }

        if (tipo == null){
            throw new RuntimeException("Tipo vazio");
        }

        if(saldoInicial == null) {
            throw new IllegalArgumentException("SaldoInicial não pode ser nulo");
        }

        if(saldo == null) {
            throw new IllegalArgumentException("Saldo não pode ser nulo");
        }
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

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    private void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    private void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    private void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

//endregion
}

