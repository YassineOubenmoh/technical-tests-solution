package org.yassine;

import org.yassine.model.Account;
import org.yassine.services.AccountService;
import org.yassine.services.AccountServiceImpl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Clock fixedClock = Clock.fixed(
                LocalDate.of(2012, 1, 10)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()
        );

        Clock fixedClock2 = Clock.fixed(
                LocalDate.of(2012, 1, 13)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()
        );

        Clock fixedClock3 = Clock.fixed(
                LocalDate.of(2012, 1, 14)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()
        );

        Account account = new Account(1, 0, new ArrayList<>());
        AccountService service = new AccountServiceImpl(account, fixedClock);
        service.deposit(1000);

        service = new AccountServiceImpl(account, fixedClock2);
        service.deposit(2000);

        service = new AccountServiceImpl(account, fixedClock3);
        service.withdraw(500);

        service.printStatement();
    }
}
