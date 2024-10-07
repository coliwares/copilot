package com.example.banking.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testGetAccountNumber() {
        Account account = new Account("1234567890", 1000.0);
        Assertions.assertEquals("1234567890", account.getAccountNumber());
    }

    @Test
    public void testSetAccountNumber() {
        Account account = new Account("1234567890", 1000.0);
        account.setAccountNumber("0987654321");
        Assertions.assertEquals("0987654321", account.getAccountNumber());
    }

    @Test
    public void testGetBalance() {
        Account account = new Account("1234567890", 1000.0);
        Assertions.assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void testSetBalance() {
        Account account = new Account("1234567890", 1000.0);
        account.setBalance(2000.0);
        Assertions.assertEquals(2000.0, account.getBalance());
    }
}