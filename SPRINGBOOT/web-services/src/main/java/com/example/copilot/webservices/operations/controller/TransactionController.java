package com.example.copilot.webservices.operations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.copilot.webservices.operations.service.TransactionService;
import com.example.copilot.webservices.operations.model.Transaction;
import com.example.copilot.webservices.operations.model.Account;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<List<Account>> transfer(@RequestBody Transaction transaction) {
        return transactionService.transfer(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody Transaction transaction) {
        return transactionService.withdraw(transaction);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody Transaction transaction) {
        ResponseEntity<Account> responseEntity = transactionService.deposit(transaction);
        return responseEntity;
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Account> checkBalance(@PathVariable String accountId) {
        return transactionService.checkBalance(accountId);
    }
}
