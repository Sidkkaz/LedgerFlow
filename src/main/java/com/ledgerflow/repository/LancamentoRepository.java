package com.ledgerflow.repository;

import com.ledgerflow.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepository implements Repository<Lancamento> {

    String db ="JDBC:sqlite:app.db";

    public LancamentoRepository() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                    CREATE TABLE IF NOT EXISTS Lancamento (
                    id UNIQUE PRIMARY KEY AUTOINCREMENT,
                    dia DATE DEFAULT CURRENT_TIMESTAMP,
                    descricao TEXT NOT NULL,
                    valor DOUBLE NOT NULL,
                    tipo INTEGER NOT NULL,
                    categoria_id INTEGER NOT NULL,
                    conta_id INTEGER NOT NULL,
                    status_id INTEGER NOT NULL,
                    observacao TEXT NOT NULL,
                    )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Lancamento lancamento) {
        String sql = """
                INSERT INTO Lancamento (dia, descricao, valor, tipo, categoria_id, conta_id, status_id, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2,lancamento.getDescricao());
            stmt.setDouble(3, lancamento.getValor());
            stmt.setInt(4, TipoLancamento.WhoIs(lancamento.getTipo()));
            stmt.setLong(5, lancamento.getCategoria().getId());
            stmt.setLong(6, lancamento.getConta().getId());
            stmt.setInt(7, StatusLancamento.WhoIs(lancamento.getStatus()));
            stmt.setString(8, lancamento.getObservacao());


            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Lancamento lancamento) {
        String sql = """
                    UPDATE Lancamento SET (dia, descricao, valor, tipo, categoria_id, conta_id, status_id, observacao) WHERE id = ?
            """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2,lancamento.getDescricao());
            stmt.setDouble(3, lancamento.getValor());
            stmt.setInt(4, TipoLancamento.WhoIs(lancamento.getTipo()));
            stmt.setLong(5, lancamento.getCategoria().getId());
            stmt.setLong(6, lancamento.getConta().getId());
            stmt.setInt(7, StatusLancamento.WhoIs(lancamento.getStatus()));
            stmt.setString(8, lancamento.getObservacao());
            stmt.setInt(9, lancamento.getId());

            stmt.executeUpdate();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Lancamento lancamento) {
        String sql = """
                DELETE FROM Lancamento WHERE id = ?;""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setInt(1, lancamento.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lancamento> list() {
        String sql = "SELECT * FROM Lancamento";

        List<Lancamento> lista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql)
        ){
            while(result.next()){
                var id = result.getInt("id");
                var date = result.getDate("dia");
                var descricao = result.getString("email");
                var valor = result.getDouble("valor");
                var tipo = result.getInt("tipo");
                var categoria = result.getInt("categoria_id");
                var conta = result.getInt("conta_id");
                var status = result.getInt("status_id");
                var observacao = result.getString("observacao");

                Lancamento lanc = new Lancamento(id, date.toLocalDate(), descricao,valor, TipoLancamento.Select(tipo), Categoria.Select(categoria), ContaFinanceira.create(conta), StatusLancamento.Select(status), observacao);
                lanc.setId(id);

                lista.add(lanc);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
