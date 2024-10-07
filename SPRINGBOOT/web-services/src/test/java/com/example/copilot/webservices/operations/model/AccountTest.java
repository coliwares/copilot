package com.example.copilot.webservices.operations.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

public class AccountTest {

    @Test
    @DisplayName("Test default constructor")
    public void testDefaultConstructor() {
        Account account = new Account();
        assertEquals(null, account.getAccountNumber());
        assertEquals(null, account.getName());
        assertEquals(null, account.getBalance());
    }

    @Test
    @DisplayName("Test parameterized constructor")
    public void testParameterizedConstructor() {
        Account account = new Account(123456789L, "John Doe", 1000.0);
        assertEquals(Long.valueOf(123456789), account.getAccountNumber());
        assertEquals("John Doe", account.getName());
        assertEquals(Double.valueOf(1000.0), account.getBalance());
    }

    @Test
    @DisplayName("Test set and get account number")
    public void testSetAndGetAccountNumber() {
        Account account = new Account();
        account.setAccountNumber(987654321L);
        assertEquals(Long.valueOf(987654321), account.getAccountNumber());
    }

    @Test
    public void testSetAndGetName() {
        Account account = new Account();
        account.setName("Jane Doe");
        assertEquals("Jane Doe", account.getName());
    }

    @Test
    public void testSetAndGetBalance() {
        Account account = new Account();
        account.setBalance(500.0);
        assertEquals(Double.valueOf(500.0), account.getBalance());
    }

    @Test
    public void testToString() {
        Account account = new Account(123456789L, "John Doe", 1000.0);
        String expectedString = "Account [accountNumber=123456789, name=John Doe, balance=1000.0]";
        assertEquals(expectedString, account.toString());
    }
}