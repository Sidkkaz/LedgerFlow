package com.ledgerflow.repository;

import com.ledgerflow.model.Lancamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LancamentoRepository implements Repository<Lancamento> {

    String db ="JDBC:sqlite:app.db";

    public LancamentoRepository() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS Lancamento (
                    
                    )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Lancamento add(Lancamento lancamento) {
        return null;
    }

    @Override
    public Lancamento update(Lancamento lancamento) {
        return null;
    }

    @Override
    public Lancamento delete(Lancamento lancamento) {
        return null;
    }

    @Override
    public Lancamento list(Lancamento lancamento) {
        return null;
    }
}
