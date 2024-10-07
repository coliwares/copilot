package com.example.banking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.banking.model.Account;

public class TransferServiceTest {

    @Test
    public void testTransfer_SuccessfulTransfer() {
        // Create mock accounts
        Account from = Mockito.mock(Account.class);
        Account to = Mockito.mock(Account.class);

        // Set initial balances
        Mockito.when(from.getBalance()).thenReturn(1000.0);
        Mockito.when(to.getBalance()).thenReturn(0.0);

        // Perform transfer
        TransferService transferService = new TransferService();
        boolean transferResult = transferService.transfer(from, to, 500.0);

        // Verify balances after transfer
        Mockito.verify(from).setBalance(500.0);
        Mockito.verify(to).setBalance(500.0);

        // Verify transfer result
        Assertions.assertTrue(transferResult);
    }

    @Test
    public void testTransfer_FailedTransfer() {
        // Create mock accounts
        Account from = Mockito.mock(Account.class);
        Account to = Mockito.mock(Account.class);

        // Set initial balances
        Mockito.when(from.getBalance()).thenReturn(100.0);
        Mockito.when(to.getBalance()).thenReturn(0.0);

        // Perform transfer
        TransferService transferService = new TransferService();
        boolean transferResult = transferService.transfer(from, to, 500.0);

        // Verify balances remain unchanged
        Mockito.verify(from, Mockito.never()).setBalance(Mockito.anyDouble());
        Mockito.verify(to, Mockito.never()).setBalance(Mockito.anyDouble());

        // Verify transfer result
        Assertions.assertFalse(transferResult);
    }
}