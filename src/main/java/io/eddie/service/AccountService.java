package io.eddie.service;

import io.eddie.data.Account;
import io.eddie.repository.AccountRepository;
import io.eddie.sys.Request;

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


    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public boolean signIn(Request request, String username, String password) {

        if ( !isValidRequest(username, password) ) {
            return false;
        } else {
            request.signIn(username);
        }

        return true;
    }


    public void signOut(Request request) {
        request.signOut();
    }

    public boolean isValidRequest(String username, String password) {

        Account findAccount = findByUsername(username);

        if ( findAccount == null ) {
            return false;
        }

        return findAccount.getPassword().equals(password);
    }

}
