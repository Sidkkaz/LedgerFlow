package com.ledgerflow.repository;

import com.ledgerflow.model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioRepository implements Repository<Usuario> {

    String db ="JDBC:sqlite:app.db";

    public UsuarioRepository() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS Usuario (
                    
                    )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario add(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario delete(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario list(Usuario usuario) {
        return null;
    }
}
