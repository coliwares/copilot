package com.example.copilot.webservices.operations.model;

public class AccountBalance {

    private Long accountId;
    private Double balance;

    public AccountBalance() {
    }

    public AccountBalance(Long accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }

}
