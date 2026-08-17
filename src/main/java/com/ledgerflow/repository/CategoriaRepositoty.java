package com.ledgerflow.repository;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.model.TipoLancamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoty implements Repository<Categoria> {

    String db ="JDBC:sqlite:app.db";

    public CategoriaRepositoty() {
        try (Connection conn = DriverManager.getConnection(db)){
            if(conn != null){
                System.out.println("Banco conectado com sucesso!");
            }

            String CriarTabela = """
                        CREATE TABLE IF NOT EXISTS Categoria (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome TEXT NOT NULL,
                        tipo_id INTEGER,
                        )""";

            Statement stmt = conn.createStatement();
            stmt.execute(CriarTabela);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Categoria categoria) {
        String add = """
                INSERT INTO Categoria (nome, tipo_id) VALUES (?, ?)""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(add)
        ){

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, TipoLancamento.WhoIs(categoria.getTipo()));

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Categoria categoria) {
        String alterarProduto = """
                    UPDATE produto SET (nome, tipo) WHERE id = ?
            """;

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement stmt = conn.prepareStatement(alterarProduto)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, TipoLancamento.WhoIs(categoria.getTipo()));
            stmt.setLong(3, categoria.getId());

            stmt.executeUpdate();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Categoria categoria) {
        String sql = """
                DELETE FROM Categoria WHERE id = ?;""";

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setLong(1, categoria.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> list(Categoria categoria) {
        String Selecionar = "SELECT * FROM Categoria";

        List<Categoria> lista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(Selecionar)
        ){
            while(result.next()){
                var id = result.getInt("id");
                var nome = result.getString("nome");
                int valor = result.getInt("tipo_id");


                Categoria tag = new Categoria(nome, TipoLancamento.Select(valor));
                tag.setId((long) id);

                lista.add(tag);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
