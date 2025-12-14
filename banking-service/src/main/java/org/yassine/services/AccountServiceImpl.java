package org.yassine.services;

import org.yassine.exceptions.BalanceNotSufficientException;
import org.yassine.exceptions.NegativeAmountException;
import org.yassine.exceptions.NullAmountException;
import org.yassine.model.Account;
import org.yassine.model.Transaction;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;

public class AccountServiceImpl implements AccountService{

    private final Account account;
    private final Clock clock;

    public AccountServiceImpl(Account account, Clock clock) {
        this.account = account;
        this.clock = clock;
    }

    @Override
    public void deposit(int amount) {
        if (amount == 0){
            throw new NullAmountException("No amount is added !");
        } else if (amount < 0) {
            throw new NegativeAmountException("You added a negative amount !");
        }
        Transaction transaction = new Transaction(LocalDate.now(clock), account.getBalance() + amount, amount);
        account.setBalance(transaction.getBalanceAfter());
        account.getTransactionList().add(transaction);
    }

    @Override
    public void withdraw(int amount) {
        if (amount == 0){
            throw new NullAmountException("No amount is added !");
        } else if (amount < 0) {
            throw new NegativeAmountException("You added a negative amount !");
        }
        if (account.getBalance() < amount){
            throw new BalanceNotSufficientException("You don't have enough money in your balance");
        }
        Transaction transaction = new Transaction(LocalDate.now(clock), account.getBalance() - amount, -amount);
        account.setBalance(transaction.getBalanceAfter());
        account.getTransactionList().add(transaction);
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");
        account.getTransactionList().sort(Comparator.comparing(Transaction::getTransactionTime).reversed());
        account.getTransactionList().forEach(
                transaction -> System.out.println(transaction.getTransactionTime() + " || " + transaction.getAmount() + " || " + transaction.getBalanceAfter())
        );
    }
}
