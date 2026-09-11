package com.ledgerflow.model;

import com.ledgerflow.model.enums.ContaTipo;

import java.math.BigDecimal;

public class ContaFinanceira {

    private Long id;
    private final String nome;
    private String agencia;
    private String numero;
    private final ContaTipo tipo;
    private final BigDecimal saldoInicial;
    private BigDecimal saldo;
    private boolean ativo;

    // region Construtores
    public ContaFinanceira(
            String nome,
            ContaTipo tipo,
            BigDecimal saldoInicial
    ) {
        validar(
            nome,
            tipo,
            saldoInicial
        );

        this.nome = nome;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.saldo = saldoInicial;
    }

    public static ContaFinanceira reconstruirConta(
            Long id,
            String nome,
            String agencia,
            String numero,
            ContaTipo tipo,
            BigDecimal saldoInicial,
            BigDecimal saldo,
            boolean ativo
    ) {
        ContaFinanceira c = new ContaFinanceira(nome, tipo, saldoInicial);
        c.id = id;
        c.agencia = agencia;
        c.numero = numero;
        c.saldo = saldo;
        c.ativo = ativo;
        return c;
    }
    // endregion

    // region Métodos
    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");

        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        if (saldo.compareTo(valor) < 0)
            throw new IllegalArgumentException("Saldo insuficiente");

        this.saldo = this.saldo.subtract(valor);
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    private static void validar(String nome, ContaTipo tipo, BigDecimal saldoInicial) {

        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome não pode ser vazio");

        if (tipo == null)
            throw new IllegalArgumentException("Tipo não pode ser nulo");

        if (saldoInicial == null)
            throw new IllegalArgumentException("Saldo inicial não pode ser nulo");

    }
    // endregion

    // region Get/Set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ContaTipo getTipo() {
        return tipo;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }
    // endregion
}
