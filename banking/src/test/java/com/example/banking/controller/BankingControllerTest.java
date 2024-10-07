package com.example.banking.controller;

import com.example.banking.model.Account;
import com.example.banking.request.TransferRequest;
import com.example.banking.service.TransferService;

import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BankingControllerTest {

    private BankingController bankingController;
    private TransferService transferService;

    @BeforeEach
    public void setup() {
        transferService = Mockito.mock(TransferService.class);
        bankingController = new BankingController(transferService);
    }

    @Test
    public void testTransfer_SuccessfulTransfer() {
        // Create mock request
        TransferRequest request = new TransferRequest();
        request.setFromAccount("123456789");
        request.setToAccount("987654321");
        request.setAmount(500.0);

        // Create mock accounts
        Account from = Mockito.mock(Account.class);
        Account to = Mockito.mock(Account.class);

        // Set initial balances
        Mockito.when(from.getBalance()).thenReturn(1000.0);
        Mockito.when(to.getBalance()).thenReturn(0.0);

        // Mock transfer service
        Mockito.when(transferService.transfer(any(), any(), eq(500.0))).thenReturn(true);

        // Perform transfer
        ResponseEntity<String> response = bankingController.transfer(request);

        // Verify response
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Transferencia exitosa", response.getBody());
    }

    @Test
    public void testTransfer_InsufficientFunds() {
        // Create mock request
        TransferRequest request = new TransferRequest();
        request.setFromAccount("123456789");
        request.setToAccount("987654321");
        request.setAmount(1500.0);

        // Create mock accounts
        Account from = Mockito.mock(Account.class);
        Account to = Mockito.mock(Account.class);

        // Set initial balances
        Mockito.when(from.getBalance()).thenReturn(1000.0);
        Mockito.when(to.getBalance()).thenReturn(0.0);

        // Mock transfer service
        Mockito.when(transferService.transfer(any(), any(), eq(1500.0))).thenReturn(false);

        // Perform transfer
        ResponseEntity<String> response = bankingController.transfer(request);

        // Verify response
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Fondos insuficientes", response.getBody());
    }
}