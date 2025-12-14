package org.yassine.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yassine.exceptions.BalanceNotSufficientException;
import org.yassine.exceptions.NegativeAmountException;
import org.yassine.exceptions.NullAmountException;
import org.yassine.model.Account;
import org.yassine.model.Transaction;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest{

    private Account account;
    private Clock clock;
    private AccountServiceImpl accountService;


    @BeforeEach
    public void setUp() {
        Instant fixedInstant = Instant.parse("2025-12-13T10:00:00Z");
        clock = Clock.fixed(fixedInstant, ZoneId.of("UTC"));
        account = new Account(1, 1000, new ArrayList<>());
        account.setBalance(0);
        accountService = new AccountServiceImpl(account, clock);
    }

    @Test
    void testDepositPositiveAmount() {
        accountService.deposit(100);

        assertEquals(100, account.getBalance());
        assertEquals(1, account.getTransactionList().size());

        Transaction transaction = account.getTransactionList().get(0);
        assertEquals(LocalDate.now(clock), transaction.getTransactionTime());
        assertEquals(100, transaction.getAmount());
        assertEquals(100, transaction.getBalanceAfter());
    }


    @Test
    void testDepositZeroAmountThrowsException() {
        assertThrows(NullAmountException.class, () -> accountService.deposit(0));
    }


    @Test
    void testDepositNegativeAmountThrowsException() {
        assertThrows(NegativeAmountException.class, () -> accountService.deposit(-50));
    }


    @Test
    void testWithdrawPositiveAmount() {
        accountService.deposit(200);
        accountService.withdraw(50);

        assertEquals(150, account.getBalance());
        assertEquals(2, account.getTransactionList().size());

        Transaction transaction = account.getTransactionList().get(1);
        assertEquals(-50, transaction.getAmount());
        assertEquals(150, transaction.getBalanceAfter());
    }


    @Test
    void testWithdrawZeroAmountThrowsException() {
        assertThrows(NullAmountException.class, () -> accountService.withdraw(0));
    }


    @Test
    void testWithdrawNegativeAmountThrowsException() {
        assertThrows(NegativeAmountException.class, () -> accountService.withdraw(-30));
    }


    @Test
    void testWithdrawInsufficientBalanceThrowsException() {
        accountService.deposit(100);
        assertThrows(BalanceNotSufficientException.class, () -> accountService.withdraw(200));
    }


    @Test
    void testPrintStatement() {
        accountService.deposit(100);
        accountService.withdraw(40);

        assertDoesNotThrow(() -> accountService.printStatement());
    }
}