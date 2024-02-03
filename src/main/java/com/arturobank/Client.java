package com.arturobank;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Account account;
    private List <String> bills = new ArrayList<>();

    public Client(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public List<String> getBills() {
        return bills;
    }

    public void addBill(String bill){
        bills.add(bill);
    }
}
