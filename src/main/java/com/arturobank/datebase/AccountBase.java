package com.arturobank.datebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountBase {

    private Map<Integer, Integer> accounts = new HashMap<>();

    public Map<Integer, Integer> getAccountsBase() {
        return accounts;
    }

    public void setAccounts(Map<Integer, Integer> accounts) {
        this.accounts = accounts;
    }

    public int createAccountNumber(){
        int total = 1;
        for (int value : accounts.values()){
            total += 1;
        }
        return total;
    }
}
