package com.example.copilot.webservices.operations.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.copilot.webservices.operations.model.Account;
import com.example.copilot.webservices.operations.model.Transaction;
import com.example.copilot.webservices.operations.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    // Add the asJsonString method
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCheckBalance() throws Exception {
        String accountId = "123456789";
        String name = "John Doe";

        Account account = new Account();
        account.setAccountNumber(123456789L);
        account.setName("John Doe");
        account.setBalance(1000.0);

        when(transactionService.checkBalance(accountId)).thenReturn(ResponseEntity.ok(account));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/balance/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value(accountId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    // Add tests for methods transfer, withdraw, and deposit
    @Test
    public void testDeposit() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(123456789L);
        transaction.setAmount(500.0);

        Account account = new Account();
        account.setAccountNumber(123456789L);
        account.setName("John Doe");
        account.setBalance(1000.0);

        when(transactionService.deposit(any())).thenReturn(ResponseEntity.ok(account));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value(123456789L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    @Test
    public void testWithdraw() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(123456789L);
        transaction.setAmount(500.0);

        Account account = new Account();
        account.setAccountNumber(123456789L);
        account.setName("John Doe");
        account.setBalance(1000.0);

        when(transactionService.withdraw(any())).thenReturn(ResponseEntity.ok(account));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value(123456789L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    @Test
    public void testTransfer() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(123456789L);
        transaction.setToAccountId(987654321L);
        transaction.setAmount(500.0);

        Account account1 = new Account();
        account1.setAccountNumber(123456789L);
        account1.setName("John Doe");
        account1.setBalance(1000.0);

        Account account2 = new Account();
        account2.setAccountNumber(987654321L);
        account2.setName("Jane Doe");
        account2.setBalance(500.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(transactionService.transfer(any())).thenReturn(ResponseEntity.ok(accounts));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountNumber").value(123456789L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance").value(1000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].accountNumber").value(987654321L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].balance").value(500.0));
    }

}