package com.ledgerflow.service;

import org.mindrot.jbcrypt.BCrypt;

public class LoginService {

    private static final String emailAdm = ".";
    public static final String senhaAdm = ".";

    public static boolean ConferirLogin(String email, String senha) {
        if (email.isEmpty() || senha.isEmpty()) return false;

        var senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        if (email.equals(emailAdm) && senha.equals(senhaAdm)) {
            return true;
        }else  {
            return false;
        }
    }
}
