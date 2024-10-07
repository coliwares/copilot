package com.example.copilot.webservices.operations.utils;

import com.example.copilot.webservices.operations.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("AccountUtil Tests")
public class AccountUtilTest {

    @Test
    @DisplayName("Should return true when account is not null and balance is sufficient")
    public void testValidateAccountWithValidAccountAndSufficientBalance() {
        // Arrange
        Account account = new Account(1L, "John Doe", 1000.0);
        Double amount = 500.0;

        // Act
        boolean result = AccountUtil.validateAccount(account, amount);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when account is null")
    public void testValidateAccountWithNullAccount() {
        // Arrange
        Account account = null;
        Double amount = 500.0;

        // Act
        boolean result = AccountUtil.validateAccount(account, amount);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when account balance is insufficient")
    public void testValidateAccountWithInsufficientBalance() {
        // Arrange
        Account account = new Account(1L, "John Doe", 1000.0);
        Double amount = 1500.0;

        // Act
        boolean result = AccountUtil.validateAccount(account, amount);

        // Assert
        assertFalse(result);
    }
}