package com.ledgerflow.repository;

import java.sql.*;

public class InitDataBase {

    String db = DbConfig.bancoConexao;

    public InitDataBase() throws SQLException {
        try (
            Connection conn = DriverManager.getConnection(db)
        ){
            if (conn != null) {
                System.out.println("Banco conectado com sucesso!");
            }

            conn.createStatement().execute("PRAGMA foreign_keys = ON");

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
                    saldoInicial DOUBLE NOT NULL,
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
                    observacao TEXT NOT NULL,
                    
                    FOREIGN KEY (categoria_id) REFERENCES Categoria(id),
                    FOREIGN KEY (conta_id) REFERENCES ContaFinanceira(id)
                    )""";

            stmt = conn.createStatement();
            stmt.execute(CriarTabela);

            String CriarTabelaUsuario = """
                    CREATE TABLE IF NOT EXISTS Usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    senha TEXT NOT NULL,
                    ativo BOOLEAN NOT NULL
                    )""";

            stmt = conn.createStatement();
            stmt.execute(CriarTabelaUsuario);

            String InserirCategoria = """
                            INSERT INTO Categoria (nome)
                            VALUES (?)
                    """;

            PreparedStatement stmtinsert = conn.prepareStatement(InserirCategoria);
            stmtinsert.setString(1, "Indefinido");

            stmtinsert.executeUpdate();

            String InserirUsuario = """
                            INSERT INTO Usuario (nome, email, senha, ativo)
                            VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement stmtUser = conn.prepareStatement(InserirUsuario);
            stmtUser.setString(1, "Heitor Lindão");
            stmtUser.setString(2, "adm@ledgerflow.com");
            stmtUser.setString(3, "cdb4ee2aea69cc6a83331bbe96dc2caa9a299d21329efb0336fc02a82e1839a8");
            stmtUser.setBoolean(4, true);

            stmtUser.executeUpdate();
            //não me enche o saco, tive de fazer input manual para teste

        }catch(SQLException erro){
            throw new SQLException(erro);
        }
    }
}
