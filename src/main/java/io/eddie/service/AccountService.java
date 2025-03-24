package io.eddie.service;

import io.eddie.data.Account;
import io.eddie.repository.AccountRepository;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(
            String username, String password, String name, String email
    ) {
        return accountRepository.save(username, password, name, email);
    }

    public Account getById(int id) {
        return accountRepository.getById(id);
    }

    public boolean remove(int id) {
        return accountRepository.remove(id);
    }

    public void update(int id, String password, String email) {
        accountRepository.update(id, password, email);
    }


}
