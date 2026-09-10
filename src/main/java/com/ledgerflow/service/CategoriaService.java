package com.ledgerflow.service;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.repository.CategoriaRepositoty;
import com.ledgerflow.repository.Repository;

import java.util.List;

public class CategoriaService {

    private final Repository<Categoria> repo = new CategoriaRepositoty();

    public void CriarCategoria(Categoria categoria) {
        repo.add(categoria);
    }

    public void AtualizarTipoCategoria(Categoria categoria) {
        repo.update(categoria);
    }

    public List<Categoria> ListarCategorias() {
        return repo.list();
    }
}
