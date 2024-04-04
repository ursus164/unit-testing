package org.ursus.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private List<Account> prepareAccountData() {
        Address address1 = new Address("Krakowska", "Kraków", "30-303", "Poland");
        Address address2 = new Address("Warszawska", "Warszawa", "30-303", "Poland");

        Account account1 = new Account(address1);
        Account account2 = new Account(address2);
        Account account3 = new Account();

        return Arrays.asList(account1, account2, account3);
    }

    @Test
    void getAllActiveAccounts() {
        // given
        List<Account> accounts = prepareAccountData();
        AccountRepo accountRepo = mock(AccountRepo.class);
        AccountService accountService = new AccountService(accountRepo);

        given(accountRepo.getAllAccounts()).willReturn(accounts);
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList.size(), is(equalTo(2)));
    }

    @Test
    void getAllInactiveAccounts() {
        // given
        List<Account> accounts = prepareAccountData();
        // by default, the mock returns an empty list, because we haven't defined any behavior
        AccountRepo accountRepo = mock(AccountRepo.class);
        AccountService accountService = new AccountService(accountRepo);

        // if we call the method getAllAccounts() on the mock, we want it to return the list of accounts
        given(accountRepo.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllInactiveAccounts();

        //then
        assertThat(accountList.size(), is(equalTo(1)));
    }
    @Test
    void testIfRepositoryContainsNoAccounts() {
        // given
        AccountRepo accountRepo = mock(AccountRepo.class);
        AccountService accountService = new AccountService(accountRepo);

        // if we call the method getAllAccounts() on the mock, we want it to return the list of accounts
        given(accountRepo.getAllAccounts()).willReturn(List.of());



        //when
        List<Account> accountList = accountService.getAllInactiveAccounts();

        //then
        assertThat(accountList.size(), is(equalTo(0)));
        assertThat(accountList, is(emptyCollectionOf(Account.class)));
    }
}
