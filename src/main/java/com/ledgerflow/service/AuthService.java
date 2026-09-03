package com.ledgerflow.service;

import com.ledgerflow.model.Sessao;
import com.ledgerflow.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class AuthService {

    private final UsuarioService UserService;
    private Usuario userAtual;

    public AuthService(UsuarioService UserService) {
        this.UserService = UserService;
    }

    public boolean Login(String email, String senha) throws SQLException {
        if (email.isEmpty() || senha.isEmpty()) return false;
        var senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        var user = UserService.findByEmail(email);
        if (user == null) return false;

        if(user.getSenha().equals(senhaHash) && user.getEmail().equals(email)) {
            userAtual = user;
            return true;
        }

        return false;
    }

    public Usuario getUserAtual() {
        return userAtual;
    }
}
