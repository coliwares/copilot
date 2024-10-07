package com.example.copilot.webservices.operations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String name;
    private Double balance;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(Long accountNumber, String name, Double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance
                + "]";
    }

}
