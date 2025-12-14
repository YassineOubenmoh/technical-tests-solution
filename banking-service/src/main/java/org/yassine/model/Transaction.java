package org.yassine.model;

import java.time.LocalDate;

public class Transaction {
    private LocalDate transactionTime;
    private int balanceAfter;
    private int amount;

    public Transaction(LocalDate transactionTime, int balanceAfter, int amount) {
        this.transactionTime = transactionTime;
        this.balanceAfter = balanceAfter;
        this.amount = amount;
    }

    public LocalDate getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDate transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(int balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
