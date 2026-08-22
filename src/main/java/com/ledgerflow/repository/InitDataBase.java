package com.ledgerflow.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {
    String db ="JDBC:sqlite:app.db";

    public InitDataBase() throws SQLException {
        Connection conn = DriverManager.getConnection(db);

        if(conn != null){
            System.out.println("Banco conectado com sucesso!");
        }

        String CriarTabelaCategoria = """
                        CREATE TABLE IF NOT EXISTS Categoria (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        tipo_id INTEGER
                        )""";

        Statement stmt = conn.createStatement();
        stmt.execute(CriarTabelaCategoria);

        String CriarTabelaContas = """
                    CREATE TABLE IF NOT EXISTS ContaFinanceira (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    agencia INTEGER NOT NULL,
                    numero INTEGER NOT NULL,
                    conta_tipo INTEGER NOT NULL,
                    saldo DOUBLE NOT NULL,
                    ativo BOOLEAN NOT NULL
                    )""";

        stmt = conn.createStatement();
        stmt.execute(CriarTabelaContas);

        String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS Lancamento (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    dia DATE DEFAULT CURRENT_TIMESTAMP,
                    descricao TEXT NOT NULL,
                    valor DOUBLE NOT NULL,
                    tipo INTEGER NOT NULL,
                    categoria_id INTEGER NOT NULL,
                    conta_id INTEGER NOT NULL,
                    status_id INTEGER NOT NULL,
                    observacao TEXT NOT NULL
                    )""";

        stmt = conn.createStatement();
        stmt.execute(CriarTabela);

        String CriarTabelaUsuario = """
                    CREATE TABLE IF NOT EXISTS Usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    senha TEXT NOT NULL,
                    perfil_id INTEGER NOT NULL,
                    ativo BOOLEAN NOT NULL
                    )""";

        stmt = conn.createStatement();
        stmt.execute(CriarTabelaUsuario);

    }
}
