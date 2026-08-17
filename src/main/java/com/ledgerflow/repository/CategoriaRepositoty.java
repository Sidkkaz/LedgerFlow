package com.ledgerflow.repository;

import com.ledgerflow.model.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriaRepositoty implements Repository<Categoria> {

    String db ="JDBC:sqlite:app.db";

    public CategoriaRepositoty() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                        CREATE TABLE IF NOT EXISTS Categoria (
                        
                        )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria add(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria update(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria delete(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria list(Categoria categoria) {
        return null;
    }
}
