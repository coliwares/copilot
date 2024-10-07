package com.example.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.Account;
import com.example.banking.request.TransferRequest;
import com.example.banking.service.TransferService;

@RestController
@RequestMapping("/banking")
public class BankingController {

    private final TransferService transferService;

    public BankingController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        Account from = new Account(request.getFromAccount(), 1000); // Monto inicial para pruebas
        Account to = new Account(request.getToAccount(), 500); // Monto inicial para pruebas

        boolean success = transferService.transfer(from, to, request.getAmount());
        if (success) {
            return ResponseEntity.ok("Transferencia exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fondos insuficientes");
        }
    }
}
