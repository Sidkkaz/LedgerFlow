package com.ledgerflow.service;

public class LoginService {

    public static boolean ConferirLogin(String email, String senha) {
        if (email.isEmpty() || senha.isEmpty()) return false;

        if (email.equals("admin") && senha.equals("3031")) {
            return true;
        }else  {
            return false;
        }
    }
}
