package com.ledgerflow.repository;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.enums.ContaTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaFinanceiraRepository implements Repository<ContaFinanceira> {

    String db = DbConfig.bancoConexao;

    @Override
    public void add(ContaFinanceira c) {
        String sql = """
                INSERT INTO ContaFinanceira
                (nome, agencia, numero, conta_tipo, saldoInicial, saldo, ativo)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getAgencia());
            stmt.setString(3, c.getNumero());
            stmt.setInt(4, c.getTipo().getValue());
            stmt.setBigDecimal(5, c.getSaldoInicial());
            stmt.setBigDecimal(6, c.getSaldo());
            stmt.setBoolean(7, c.isAtivo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ContaFinanceira c) {
        String sql = """
                UPDATE ContaFinanceira
                SET saldo = ?, ativo = ?
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, c.getSaldo());
            stmt.setBoolean(2, c.isAtivo());
            stmt.setLong(3, c.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(ContaFinanceira c) {
        throw new UnsupportedOperationException("Contas não são excluídas — use desativar()");
    }

    @Override
    public List<ContaFinanceira> list() {
        String sql = "SELECT * FROM ContaFinanceira";

        List<ContaFinanceira> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {
                ContaFinanceira c = ContaFinanceira.reconstruirConta(
                        result.getLong("id"),
                        result.getString("nome"),
                        result.getString("agencia"),
                        result.getString("numero"),
                        ContaTipo.fromValue(result.getInt("conta_tipo")),
                        result.getBigDecimal("saldoInicial"),
                        result.getBigDecimal("saldo"),
                        result.getBoolean("ativo")
                );

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}