package org.ursus.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepoStub implements AccountRepo {
    @Override
    public List<Account> getAllAccounts() {
        Address address1 = new Address("Krakowska", "Kraków", "30-303", "Poland");
        Address address2 = new Address("Warszawska", "Warszawa", "30-303", "Poland");

        Account account1 = new Account(address1);
        Account account2 = new Account(address2);
        Account account3 = new Account();

        return Arrays.asList(account1, account2, account3);
    }
}

