package com.ledgerflow.repository;

import com.ledgerflow.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario> {

    String db = DbConfig.bancoConexao;

    @Override
    public void add(Usuario usuario) {
        String sql = """
                INSERT INTO Usuario (nome, email, senha)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                UPDATE Usuario
                SET nome = ?, email = ?, senha = ?
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setLong(4, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        String sql = """
                DELETE FROM Usuario
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> list() {
        String sql = "SELECT * FROM Usuario";

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {

                long id = result.getLong("id");
                String nome = result.getString("nome");
                String email = result.getString("email");
                String senha = result.getString("senha");

                Usuario usuario = new Usuario(
                        id,
                        nome,
                        email,
                        senha
                );

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Usuario findByEmail(String email) throws SQLException {

        String sql = """
                SELECT * FROM Usuario
                WHERE email = ?
                """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet result = stmt.executeQuery()) {

                if (result.next()) {

                    long id = result.getLong("id");
                    String nome = result.getString("nome");
                    String senha = result.getString("senha");

                    return new Usuario(
                            id,
                            nome,
                            email,
                            senha
                    );
                }
            }
        }

        return null;
    }
}