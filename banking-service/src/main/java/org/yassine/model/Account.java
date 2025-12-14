package org.yassine.model;


import java.util.List;

public class Account {
    private int accountId;
    private int balance;
    private List<Transaction> transactionList;

    public Account(int accountId, int balance, List<Transaction> transactionList) {
        this.accountId = accountId;
        this.balance = balance;
        this.transactionList = transactionList;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
