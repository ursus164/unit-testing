package org.ursus.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccounts() {
        // given
        AccountRepo accountRepoStub = new AccountRepoStub();
        AccountService accountService = new AccountService(accountRepoStub);
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList.size(), is(equalTo(2)));
    }
}


