package com.ledgerflow.service;

import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.model.Usuario;
import java.sql.SQLException;
import static org.apache.commons.codec.digest.DigestUtils.*;

public class AuthService {

    private final UsuarioService userService;
    private Usuario userAtual;

    public AuthService(UsuarioService userService) {
        this.userService = userService;
    }

    public boolean login(String email, String senha) throws SQLException {
        if (email.isBlank() || senha.isBlank()) return false;

        var senhaHash = sha256Hex(senha);

        var user = userService.findByEmail(email);
        if (user == null) {
            PopupWarning.warning("Sem cadastro","Email não encontrado");
            return false;
        }

        if(user.getSenha().equals(senhaHash)){
            userAtual = user;
            return true;
        }

        return false;
    }

    public boolean registro(String nome, String email, String senha) throws SQLException {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) return false;

        var user = userService.findByEmail(email);
        if (user != null) {
            PopupWarning.warning("Cadastro Existente","O email informado já possui cadastrado no sistema");
            return false;
        }

        var senhaHash = sha256Hex(senha);

        return userService.Adicionar(new Usuario(nome, email, senhaHash, true));
    }

    public Usuario getUserAtual() {
        return userAtual;
    }
}
