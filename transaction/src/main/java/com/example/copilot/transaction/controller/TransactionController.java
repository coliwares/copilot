package com.example.copilot.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.copilot.transaction.model.Transaction;
import com.example.copilot.transaction.repository.TransactionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}