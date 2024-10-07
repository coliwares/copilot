package com.example.copilot.webservices.operations.service;

import com.example.copilot.webservices.operations.exception.BalanceException;
import com.example.copilot.webservices.operations.model.Account;
import com.example.copilot.webservices.operations.model.Transaction;
import com.example.copilot.webservices.operations.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account sourceAccount;
    private Account destinationAccount;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        sourceAccount = new Account(1L, "John Doe", 1000.0);
        destinationAccount = new Account(2L, "Jane Smith", 2000.0);
        transaction = new Transaction(1L, 2L, 500.0);
    }

    @Test
    public void testWithdrawFundsOK() {
        // Setup
        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));

        // Execute
        ResponseEntity<Account> response = transactionService.withdraw(new Transaction(1L, null, 200.0));

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(800.0, response.getBody().getBalance());
    }

    @Test
    public void testWithdrawFundsInsufficientBalance() {
        // Setup
        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));

        // Execute and Verify
        assertThrows(BalanceException.class, () -> {
            transactionService.withdraw(new Transaction(1L, null, 1500.0));
        });
    }

    @Test
    public void testDepositFundsOK() {
        // Setup
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));

        // Execute
        ResponseEntity<Account> response = transactionService.deposit(new Transaction(null, 2L, 300.0));

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2300.0, response.getBody().getBalance());
    }

    @Test
    public void testDepositFundsInvalidAccount() {
        // Setup
        when(accountRepository.findById(3L)).thenReturn(Optional.empty());

        // Execute and Verify
        assertThrows(BalanceException.class, () -> {
            transactionService.deposit(new Transaction(null, 3L, 300.0));
        });
    }
}