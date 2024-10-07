package com.example.copilot.webservices.operations.service;

import com.example.copilot.webservices.operations.exception.BalanceException;
import com.example.copilot.webservices.operations.model.Account;
import com.example.copilot.webservices.operations.model.Transaction;
import com.example.copilot.webservices.operations.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    // Placeholder method for transferring funds between accounts
    public ResponseEntity<List<Account>> transfer(Transaction transaction) {
        // revisar si los account from y to existen
        // si no existe, retornar un error identificando el account que no existe
        // si existe, validar si el monto a transferir es menor al balance
        // si es menor, transferir el monto
        // si es mayor, retornar un error
        Account accountFrom = accountRepository.findById(transaction.getFromAccountId())
                .orElseThrow(() -> new BalanceException("Account " + transaction.getFromAccountId() + " not found"));

        Account accountTo = accountRepository.findById(transaction.getToAccountId())
                .orElseThrow(() -> new BalanceException("Account " + transaction.getToAccountId() + " not found"));

        if (accountFrom.getBalance() < transaction.getAmount()) {
            throw new BalanceException("Insufficient funds");
        }

        Double balanceFrom = accountFrom.getBalance() - transaction.getAmount();
        accountFrom.setBalance(balanceFrom);
        accountRepository.saveAndFlush(accountFrom);

        Double balanceTo = accountTo.getBalance() + transaction.getAmount();
        accountTo.setBalance(balanceTo);
        accountRepository.saveAndFlush(accountTo);

        List<Account> accounts = new ArrayList<Account>();
        accounts.add(accountFrom);
        accounts.add(accountTo);

        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }

    // Placeholder method for withdrawing funds from an account
    public ResponseEntity<Account> withdraw(Transaction transaction) {
        // revisar si el account existe
        // si no existe, retornar un error
        // si existe, validar si el monto a retirar es menor al balance
        // si es menor, retirar el monto
        // si es mayor, retornar un error
        Account accountFrom = accountRepository.findById(transaction.getFromAccountId())
                .orElseThrow(() -> new BalanceException("Account not found"));

        if (accountFrom.getBalance() < transaction.getAmount()) {
            throw new BalanceException("Insufficient funds");
        }

        Double balance = accountFrom.getBalance() - transaction.getAmount();
        accountFrom.setBalance(balance);
        accountRepository.saveAndFlush(accountFrom);

        return new ResponseEntity<Account>(accountFrom, HttpStatus.OK);
    }

    // Placeholder method for depositing funds into an account
    public ResponseEntity<Account> deposit(Transaction transaction) {
        // revisar si el account existe
        // si no existe, retornar un error
        // si existe, depositar el monto
        Account accountTo = accountRepository.findById(transaction.getToAccountId())
                .orElseThrow(() -> new BalanceException("Account not found"));
        Double balance = accountTo.getBalance() + transaction.getAmount();
        accountTo.setBalance(balance);
        accountRepository.saveAndFlush(accountTo);

        return new ResponseEntity<Account>(accountTo, HttpStatus.OK);
    }

    // Placeholder method for checking the of an account
    public ResponseEntity<Account> checkBalance(String accountId) {

        Account account = new Account();
        // revisar si el account existe
        // si no existe, retornar un error
        // si existe, retornar el account
        account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(() -> new BalanceException("Account not found"));

        return ResponseEntity.ok(account);
    }
}
