package com.example.banking.service;

import org.springframework.stereotype.Service;

import com.example.banking.model.Account;

@Service
public class TransferService {
    public boolean transfer(Account from, Account to, double amount) {
        if (from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            return true;
        }
        return false;
    }
}
