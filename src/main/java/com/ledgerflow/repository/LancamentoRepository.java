package com.ledgerflow.repository;

import com.ledgerflow.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepository implements Repository<Lancamento> {

    String db ="JDBC:sqlite:app.db";

    @Override
    public void add(Lancamento lancamento) {
        String sql = """
                INSERT INTO Lancamento (dia, descricao, valor, tipo, categoria_id, conta_id, status_id, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2,lancamento.getDescricao());
            stmt.setBigDecimal(3, lancamento.getValor());
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
                    UPDATE Lancamento SET dia = ?, descricao = ?, valor = ?, tipo = ?, categoria_id = ?, conta_id = ?, status_id = ?, observacao = ? WHERE id = ?
            """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(lancamento.getData()));
            stmt.setString(2,lancamento.getDescricao());
            stmt.setBigDecimal(3, lancamento.getValor());
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
                var descricao = result.getString("descricao");
                var valor = result.getBigDecimal("valor");
                var tipo = result.getInt("tipo");
                var categoria = result.getInt("categoria_id");
                var conta = result.getInt("conta_id");
                var status = result.getInt("status_id");
                var observacao = result.getString("observacao");

                Lancamento lanc = new Lancamento(date.toLocalDate(), descricao,valor, TipoLancamento.Select(tipo), Categoria.Select(categoria), ContaFinanceira.create(conta), StatusLancamento.Select(status), observacao);
                lanc.setId(id);

                lista.add(lanc);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Lancamento findById(int id) {
        String sql = "SELECT * FROM Lancamento WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);

            try (ResultSet result = stmt.executeQuery(sql)){
                if(result.next()) {
                    var date = result.getDate("dia");
                    var descricao = result.getString("descricao");
                    var valor = result.getBigDecimal("valor");
                    var tipo = result.getInt("tipo");
                    var categoria = result.getInt("categoria_id");
                    var conta = result.getInt("conta_id");
                    var status = result.getInt("status_id");
                    var observacao = result.getString("observacao");

                    var lanc = new Lancamento(date.toLocalDate(), descricao, valor, TipoLancamento.Select(tipo), Categoria.Select(categoria), ContaFinanceira.create(conta), StatusLancamento.Select(status), observacao);
                    lanc.setId(id);

                    return lanc;
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Lancamento> findByCategoria(Categoria categoria) {
        String sql = "SELECT * FROM Lancamento WHERE categoria_id = ?";

        List<Lancamento> lista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setLong(1, categoria.getId());

            try(ResultSet result = stmt.executeQuery(sql)){
                while (result.next()) {
                    var id = result.getInt("id");
                    var date = result.getDate("dia");
                    var descricao = result.getString("descricao");
                    var valor = result.getBigDecimal("valor");
                    var tipo = result.getInt("tipo");
                    var conta = result.getInt("conta_id");
                    var status = result.getInt("status_id");
                    var observacao = result.getString("observacao");

                    Lancamento lanc = new Lancamento(date.toLocalDate(), descricao, valor, TipoLancamento.Select(tipo), categoria, ContaFinanceira.create(conta), StatusLancamento.Select(status), observacao);
                    lanc.setId(id);

                    lista.add(lanc);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Lancamento> findByCategoria(ContaFinanceira conta) throws Exception {
        String sql = "SELECT * FROM Lancamento WHERE categoria_id = ?";

        List<Lancamento> lista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, conta.getId());

            try(ResultSet result = stmt.executeQuery(sql)){
                while (result.next()) {
                    var id = result.getInt("id");
                    var date = result.getDate("dia");
                    var descricao = result.getString("descricao");
                    var valor = result.getBigDecimal("valor");
                    var tipo = result.getInt("tipo");
                    var cat = result.getInt("categoria_id");
                    var status = result.getInt("status_id");
                    var observacao = result.getString("observacao");

                    Lancamento lanc = new Lancamento(date.toLocalDate(), descricao, valor, TipoLancamento.Select(tipo), Categoria.Select(cat), conta, StatusLancamento.Select(status), observacao);
                    lanc.setId(id);

                    lista.add(lanc);
                }
            }

        }catch (SQLException e) {
            throw new Exception("Tá fudido");
        }

        return lista;
    }

}
