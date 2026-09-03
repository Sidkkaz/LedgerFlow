package com.ledgerflow.service;

import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.model.Usuario;
import java.sql.SQLException;
import static org.apache.commons.codec.digest.DigestUtils.*;

public class AuthService {

    private final UsuarioService userService;
    private Usuario userAtual;

    public AuthService(UsuarioService UserService) {
        this.userService = UserService;
    }

    public boolean Login(String email, String senha) throws SQLException {
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

    public Usuario getUserAtual() {
        return userAtual;
    }
}
