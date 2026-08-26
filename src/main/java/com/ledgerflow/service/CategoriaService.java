package com.ledgerflow.service;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.repository.CategoriaRepositoty;
import com.ledgerflow.repository.Repository;

import java.util.List;

public class CategoriaService {

    private static final Repository<Categoria> repo = new CategoriaRepositoty();

    public static void CriarCategoria(Categoria categoria) {
        repo.add(categoria);
    }

    public static void AtualizarTipoCategoria(Categoria categoria) {
        repo.update(categoria);
    }

    public static List<Categoria> ListarCategorias() {
        return repo.list();
    }
}
