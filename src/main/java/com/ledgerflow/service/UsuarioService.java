package com.ledgerflow.service;

import com.ledgerflow.model.Usuario;
import com.ledgerflow.repository.Repository;
import com.ledgerflow.repository.UsuarioRepository;

import java.sql.SQLException;

public class UsuarioService {

    private final Repository<Usuario> repo = new UsuarioRepository();

    public Usuario findByEmail(String email) throws SQLException {
        return ((UsuarioRepository) repo).findByEmail(email);
    }

}
