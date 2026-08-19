package com.ledgerflow.service;

public class LoginService {

    public static boolean ConferirLogin(String email, String senha) {
        if (email.isEmpty() || senha.isEmpty()) return false;

        if (email.equals(".") && senha.equals(".")) {
            return true;
        }else  {
            return false;
        }
    }
}
