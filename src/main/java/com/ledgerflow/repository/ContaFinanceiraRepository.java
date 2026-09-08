package com.ledgerflow.repository;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.enums.ContaTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaFinanceiraRepository implements Repository<ContaFinanceira> {

    String db = "JDBC:sqlite:app.db";

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
            stmt.setInt(2, c.getAgencia());
            stmt.setInt(3, c.getNumero());
            stmt.setInt(4, ContaTipo.getValue());
            stmt.setBigDecimal(5, c.getSaldoInicial());
            stmt.setBigDecimal(6, c.getSaldo());
            stmt.setBoolean(7, c.isAtivo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
        return;
    }

    @Override
    public List<ContaFinanceira> list() {
        String sql = "SELECT * FROM ContaFinanceira";

        List<ContaFinanceira> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {

                var id = result.getLong("id");
                var nome = result.getString("nome");
                var agencia = result.getInt("agencia");
                var numero = result.getInt("numero");
                var contaTipo = result.getInt("conta_tipo");
                var saldoInicial = result.getBigDecimal("saldoInicial");
                var saldo = result.getBigDecimal("saldo");
                var ativo = result.getBoolean("ativo");

                ContaFinanceira u = new ContaFinanceira(
                        id,
                        nome,
                        ContaTipo.fromValue(contaTipo),
                        saldoInicial,
                        saldo
                );

                u.setAgencia(agencia);
                u.setNumero(numero);
                u.setAtivo(ativo);

                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}