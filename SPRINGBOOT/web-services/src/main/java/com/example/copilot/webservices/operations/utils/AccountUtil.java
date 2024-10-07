package com.example.copilot.webservices.operations.utils;

import com.example.copilot.webservices.operations.model.Account;

public class AccountUtil {
    // utilitario para validar si el account existe y si los saldos son suficientes
    // para hacer la transaccion
    // retorna true si se puede hacer la transaccion, false en caso contrario
    public static boolean validateAccount(Account account, Double amount) {
        if (account == null) {
            return false;
        }
        if (account.getBalance() < amount) {
            return false;
        }
        return true;
    }

}
