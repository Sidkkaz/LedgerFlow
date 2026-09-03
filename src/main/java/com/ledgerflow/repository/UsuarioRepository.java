package com.ledgerflow.repository;

import com.ledgerflow.model.Perfil;
import com.ledgerflow.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario> {

    String db = "JDBC:sqlite:app.db";

    @Override
    public void add(Usuario u) {
        String sql = """
                INSERT INTO Usuario (nome, email, senha, perfil_id, ativo) VALUES (?, ?, ?, ?, ?)""";

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getPerfil().getId());
            stmt.setBoolean(5, u.isAtivo());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                    UPDATE Usuario SET nome = ?, email = ?, senha = ?, ativo = ? WHERE id = ?
            """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setBoolean(4, usuario.isAtivo());
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        String sql = """
                DELETE FROM Usuario WHERE id = ?;""";

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> list() {
        String sql = "SELECT * FROM Usuario";

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(sql)
        ) {
            while (result.next()) {
                var id = result.getInt("id");
                var nome = result.getString("nome");
                var email = result.getString("email");
                var senha = result.getString("senha");
                var perfil = result.getInt("perfil_id");
                var ativo = result.getBoolean("ativo");

                Usuario u = new Usuario(nome, email, senha, Perfil.EnviarPerfil(perfil), ativo);
                u.setId(id);

                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario findByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet result = stmt.executeQuery(sql)) {
                if (result.next()) {
                    var id = result.getInt("id");
                    var nome = result.getString("nome");
                    var senha = result.getString("senha");
                    var perfil = result.getInt("perfil_id");
                    var ativo = result.getBoolean("ativo");

                    Usuario u = new Usuario(nome, email, senha, Perfil.EnviarPerfil(perfil), ativo);
                    u.setId(id);
                    return u;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}