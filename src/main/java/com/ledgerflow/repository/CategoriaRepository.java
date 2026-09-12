package com.ledgerflow.repository;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.model.enums.TipoLancamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository implements Repository<Categoria> {

    String db = DbConfig.bancoConexao;

    @Override
    public void add(Categoria categoria) {
        String sql = """
                INSERT INTO Categoria (nome, tipo_id, ativo)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getTipo().getValue());
            stmt.setBoolean(3, categoria.isAtivo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Categoria categoria) {
        String sql = """
                UPDATE Categoria
                SET nome = ?, tipo_id = ?, ativo = ?
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getTipo().getValue());
            stmt.setBoolean(3, categoria.isAtivo());
            stmt.setLong(4, categoria.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Categoria categoria) {
        String sql = """
                DELETE FROM Categoria
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, categoria.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categoria> list() {
        String sql = "SELECT * FROM Categoria";

        List<Categoria> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {

                long id = result.getLong("id");
                String nome = result.getString("nome");
                int tipo = result.getInt("tipo_id");

                Categoria categoria = new Categoria(
                        id,
                        nome,
                        TipoLancamento.fromValue(tipo)
                );

                lista.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}