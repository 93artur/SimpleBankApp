package com.arturobank;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String userName;
    private int password;

    private int accountNumber;
    private List<String> bills = new ArrayList<>();

    public Client(String userName, int password) {
        this.userName = userName;
        this.password = password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getBills() {
        return bills;
    }

    public void addBill(String bill) {
        bills.add(bill);
    }
}
