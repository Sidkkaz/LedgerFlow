package com.ledgerflow.repository;

import com.ledgerflow.model.Perfil;
import com.ledgerflow.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario> {

    String db ="JDBC:sqlite:app.db";

    public UsuarioRepository() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS Usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    senha TEXT NOT NULL,
                    perfil_id INTEGER NOT NULL,
                    ativo BOOLEAN NOT NULL
                    )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Usuario u) {
        String sql = """
                INSERT INTO Usuario (nome, email, senha, perfil_id, ativo) VALUES (?, ?, ?, ?, ?)""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getPerfil().getId());
            stmt.setBoolean(5, u.isAtivo());


            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                    UPDATE Usuario SET (nome, email, senha, ativo) WHERE id = ?
            """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(1, usuario.getNome());
            stmt.setString(1, usuario.getNome());
            stmt.setBoolean(1, usuario.isAtivo());
            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        String sql = """
                DELETE FROM Usuario WHERE id = ?;""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> list() {
        String sql = "SELECT * FROM Usuario";

        List<Usuario> lista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql)
        ){
            while(result.next()){
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

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
