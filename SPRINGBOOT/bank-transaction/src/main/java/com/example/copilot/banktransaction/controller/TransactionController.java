package com.example.copilot.banktransaction.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @GetMapping("getAccounts")
    public String getAccounts() {
        return "Hello Accounts";
    }

}