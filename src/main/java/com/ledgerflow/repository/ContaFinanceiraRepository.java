package com.ledgerflow.repository;

import com.ledgerflow.model.ContaFinanceira;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ContaFinanceiraRepository implements Repository<ContaFinanceira> {

    String db ="JDBC:sqlite:app.db";

    public ContaFinanceiraRepository(){
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS ContaFinanceira (
                    
                    )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContaFinanceira add(ContaFinanceira contaFinanceira) {
        return null;
    }

    @Override
    public ContaFinanceira update(ContaFinanceira contaFinanceira) {
        return null;
    }

    @Override
    public ContaFinanceira delete(ContaFinanceira contaFinanceira) {
        return null;
    }
    @Override
    public ContaFinanceira list(ContaFinanceira contaFinanceira) {
        return null;
    }
}
