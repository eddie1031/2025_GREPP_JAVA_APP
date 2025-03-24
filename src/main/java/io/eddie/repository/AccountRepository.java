package io.eddie.repository;

import io.eddie.data.Account;
import io.eddie.data.AuthType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountRepository {

    private int sequence = 0;
    private final List<Account> accountList = new ArrayList<>();

    public Account saveAdmin(String username, String password, String name, String email) {

        sequence++;
        Account admin = new Account(
                sequence, username, password, name, email, AuthType.ADMIN
        );
        accountList.add(admin);

        return admin;
    }

    public Account save(
            String username, String password, String name, String email
    ) {

        sequence++;

        Account newAccount = new Account(
                sequence, username, password, name, email, AuthType.MEMBER
        );

        this.accountList.add(newAccount);

        return newAccount;
    }

    public Account getById(int id) {
        return accountList.get(id - 1);
    }

    public boolean remove(int id) {
        Account findAccount = getById(id);

        if ( findAccount == null ) {
            return false;
        } else {
            accountList.set(id - 1, null);
            return true;
        }

    }

    public void update(int id, String password, String email) {

        Account findAccount = getById(id);

        if ( findAccount == null ) {
            throw new NoSuchElementException("[404] 해당 계정은 찾을 수 없습니다.");
        }

        findAccount.setPassword(password);
        findAccount.setEmail(email);

        findAccount.setUpdatedAt(LocalDate.now());

    }

    public Account findByUsername(String username) {
        for (Account account : accountList) {

            if ( account == null ) {
                continue;
            }

            if ( account.getUsername().equals(username)) {
                return account;
            }

        }
        return null;
    }

}
