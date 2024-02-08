package com.arturobank;

import java.util.HashMap;
import java.util.Map;

public class AccountBase {
    private Map<Integer, Integer> accounts = new HashMap<>();

    public Map<Integer, Integer> getAccountsBase() {
        return accounts;
    }

    public void addAccount(int accountNumber, int count) {
        accounts.put(accountNumber, count);
    }

    public int getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void changeCount(int accountNumber, int count) {
        accounts.replace(accountNumber, count);
    }
}
