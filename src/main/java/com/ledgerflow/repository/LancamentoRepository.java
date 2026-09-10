package com.ledgerflow.repository;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.Lancamento;
import com.ledgerflow.model.enums.ContaTipo;
import com.ledgerflow.model.enums.TipoLancamento;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepository implements Repository<Lancamento> {

    TipoLancamento tl;
    String db = DbConfig.bancoConexao;

    @Override
    public void add(Lancamento lancamento) {
        String sql = """
                INSERT INTO Lancamento
                (dia, descricao, valor, tipo, categoria_id, conta_id, observacao)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2, lancamento.getDescricao());
            stmt.setBigDecimal(3, lancamento.getValor());
            stmt.setInt(4, tl.getValue());
            stmt.setLong(5, lancamento.getCategoria().getId());
            stmt.setLong(6, lancamento.getConta().getId());
            stmt.setString(7, lancamento.getObservacao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Lancamento lancamento) {
        String sql = """
                UPDATE Lancamento
                SET dia = ?,
                    descricao = ?,
                    valor = ?,
                    tipo = ?,
                    categoria_id = ?,
                    conta_id = ?,
                    observacao = ?
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2, lancamento.getDescricao());
            stmt.setBigDecimal(3, lancamento.getValor());
            stmt.setInt(4, tl.getValue());
            stmt.setLong(5, lancamento.getCategoria().getId());
            stmt.setLong(6, lancamento.getConta().getId());
            stmt.setString(7, lancamento.getObservacao());
            stmt.setLong(8, lancamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Lancamento lancamento) {
        String sql = """
                DELETE FROM Lancamento
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, lancamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lancamento> list() {
        String sql = """
        SELECT
            l.id AS lancamento_id,
            l.dia,
            l.descricao,
            l.valor,
            l.tipo_id AS lancamento_tipo,
            l.observacao,

            c.id AS categoria_id,
            c.nome AS categoria_nome,

            cf.id AS conta_id,
            cf.nome AS conta_nome,
            cf.agencia,
            cf.numero,
            cf.conta_tipo,
            cf.saldoInicial,
            cf.saldo,
            cf.ativo

        FROM Lancamento l
        JOIN Categoria c ON c.id = l.categoria_id
        JOIN ContaFinanceira cf ON cf.id = l.conta_id
        """;

        List<Lancamento> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {

                long id = result.getLong("lancamento_id");
                LocalDate data = result.getDate("dia").toLocalDate();
                String descricao = result.getString("descricao");
                BigDecimal valor = result.getBigDecimal("valor");
                int tipo = result.getInt("lancamento_tipo");
                String observacao = result.getString("observacao");

                Categoria categoria = new Categoria(
                        result.getLong("categoria_id"),
                        result.getString("categoria_nome"),
                        TipoLancamento.fromValue(tipo)
                );

                ContaFinanceira conta = new ContaFinanceira(
                        result.getLong("conta_id"),
                        result.getString("conta_nome"),
                        ContaTipo.fromValue(result.getInt("conta_tipo")),
                        result.getBigDecimal("saldoInicial"),
                        result.getBigDecimal("saldo")
                );

                conta.setAgencia(result.getString("agencia"));
                conta.setNumero(result.getString("numero"));
                var ativo = (result.getBoolean("ativo"));

                if (ativo) {
                    conta.ativar();
                }else {
                    conta.desativar();
                }

                Lancamento lancamentos = new Lancamento(
                        id,
                        data,
                        descricao,
                        valor,
                        TipoLancamento.fromValue(tipo),
                        categoria,
                        conta
                );

                lancamentos.setObservacao(observacao);

                lista.add(lancamentos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}