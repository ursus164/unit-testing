package org.ursus.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private AccountRepo accountRepo;
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    List<Account> getAllActiveAccounts() {
        return accountRepo.getAllAccounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }
    List<Account> getAllInactiveAccounts() {
        return accountRepo.getAllAccounts().stream()
                .filter(account -> !account.isActive())
                .collect(Collectors.toList());
    }
}
