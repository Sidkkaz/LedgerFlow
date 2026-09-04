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
        if (nome == null){
            throw new RuntimeException("Nome vazio");
        }

        this.nome = nome;
        this.tipo =  tipo;

        if(saldoInicial == null || saldo == null) {
            throw new IllegalArgumentException("Saldo não pode ser nulo.");
        }

        this.saldoInicial = saldoInicial;
        this.saldo = saldoInicial;
    }

    public void Depositar(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) return;

        this.saldo = this.saldo.add(valor);
    }

    public void Sacar(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) return;
        if(saldo.compareTo(BigDecimal.ZERO) < 0) return ;

        this.saldo = this.saldo.subtract(valor);
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

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

//endregion
}

